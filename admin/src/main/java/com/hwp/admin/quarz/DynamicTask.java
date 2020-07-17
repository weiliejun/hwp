package com.hwp.admin.quarz;

import com.hwp.admin.app.event.RwtxSendEmailEvent;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableAsync(
        mode = AdviceMode.PROXY, proxyTargetClass = false,
        order = Ordered.HIGHEST_PRECEDENCE
)
public class DynamicTask implements SchedulingConfigurer {
    private static final ExecutorService es = new ThreadPoolExecutor(10, 20,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10),
            new DynamicTaskConsumeThreadFactory());
    private static Logger logger = LoggerFactory.getLogger(DynamicTask.class);
    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledFutures = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CronTask> cronTasks = new ConcurrentHashMap<>();
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private XmrwxxService xmrwxxService;
    @Autowired
    private ApplicationContext applicationContext;
    private volatile ScheduledTaskRegistrar registrar;
//    private volatile List<TaskConstant> taskConstants = Lists.newArrayList();
    private volatile Map<String, String> taskConstants = new HashMap<String, String>();
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public Map<String, String> getTaskConstants() {
        return taskConstants;
    }

    public void setTaskConstants(Map<String, String> taskConstants) {
        this.taskConstants = taskConstants;
    }
/*private static final String DEFAULT_CRON = "0/5 * * * * ?";
    private String cron = DEFAULT_CRON;
    public void setCron(String cron) {
        System.out.println("当前cron="+this.cron+"->将被改变为："+cron);
        this.cron = cron;
    }*/
    /**
     * 创建ThreadPoolTaskScheduler线程池
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(8);
        threadPoolTaskScheduler.setThreadNamePrefix("task-hwp-");
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(false);
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

    public void execute() {
        destroy();

        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("today", DateHelper.getYMDFormatDate(new Date()));
        final List<Xmrwxx> results = (List<Xmrwxx>) xmrwxxService.listXmrwxxByParams(params);
        for (Xmrwxx xmrwxx : results) {
            JSONArray rwxqJson = JSONArray.fromObject(xmrwxx.getRwxq()); // 首先把字符串转成 JSONArray 对象
            //循环子任务
            for (int i = 0; i < rwxqJson.size(); i++) {
                JSONObject job = rwxqJson.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String rwmc = job.get("rwmc").toString();
//                String cron=taskConstants.get(rwmc);
                String cron = job.get("cron").toString();

                if (StringHelper.isBlank(cron)) {
                    //没有设置定时提醒
                    logger.debug("没有设置定时提醒");
                    continue;
                } else if (!CronSequenceGenerator.isValidExpression(cron)) {
                    //cron表达式无效
                    logger.debug("cron表达式无效");
                    continue;
                }
                if (!CollectionUtils.isEmpty(taskConstants)&&taskConstants.containsKey(rwmc)) {
                    cron=taskConstants.get(rwmc);
                }else{
                    taskConstants.put(rwmc,cron);
                }

                Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmrwxx.getXmId());

                /*TaskConstant taskConstant = new TaskConstant();
                taskConstant.setTaskId(rwmc);
                taskConstant.setCron(cron);
                for (TaskConstant task : taskConstants) {
                    if (task.getTaskId().equalsIgnoreCase(rwmc)) {
                        taskConstants.remove(task);
                        break;
                    }
                }
                if (!taskConstants.contains(taskConstant)) {
                    taskConstants.add(taskConstant);
                }*/

                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        //业务执行代码
                        applicationContext.publishEvent(new RwtxSendEmailEvent(this, xmrwxx, xmxxgl, rwmc));

                    }
                };

                Trigger trigger = new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        //执行于每一次任务的触发
                        // String cron = "这里就可以随意设置你的数据源咯";
                        if (!taskConstants.containsKey(rwmc)||StringHelper.isBlank(taskConstants.get(rwmc))) {
                            //cron为空
                            logger.debug("cron为空");
//                            taskConstants.put(rwmc,"0 0/1 * * * ? 2019");
                            return null;
                        }
                        logger.info("cron expression is [{}]", taskConstants.get(rwmc));
                        logger.info("trigger list size is [{}]", registrar.getTriggerTaskList().size());

                        CronTrigger cronTrigger = new CronTrigger(taskConstants.get(rwmc));
                        Date nextExecTime = cronTrigger.nextExecutionTime(triggerContext);
                        return nextExecTime;
                    }
                };

//                registrar.addTriggerTask(task, getTrigger(taskConstants.get(rwmc)));
                registrar.addTriggerTask(task, trigger);
            }
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setTaskScheduler(threadPoolTaskScheduler);
        this.registrar = registrar;
        logger.info("检测动态定时任务列表...");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("现在的时间是：" + sdf.format(date));

        execute();


//        refreshTasks();
    }

    public void registrarTasks() {
        if (!CollectionUtils.isEmpty(taskConstants)) {
//            List<TimingTask> tts = new ArrayList<>();
            List<TriggerTask> list = registrar.getTriggerTaskList();
            for (TriggerTask s : list) {
            }
            for (Object o : taskConstants.keySet()) {
                System.out.println("key=" + o + " value=" + taskConstants.get(o));

            }

        }
    }

    public void refreshTasks() {
        if (!CollectionUtils.isEmpty(taskConstants)) {
            List<TimingTask> tts = new ArrayList<>();
            for (Object o : taskConstants.keySet()) {
                System.out.println("key=" + o + " value=" + taskConstants.get(o));
                TimingTask tt = new TimingTask();
                tt.setExpression(taskConstants.get(o));
//                tt.setTaskId("dynamic-task-" + o);
                tt.setTaskId(o.toString());
                tts.add(tt);
            }

            this.refreshTasks(tts);
        }
    }

    /**
     * 业务触发器
     *
     * @return
     */
    private Trigger getTrigger(String cron) {
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //执行于每一次任务的触发
                // String cron = "这里就可以随意设置你的数据源咯";
                logger.info("cron expression is [{}]", cron);
                logger.info("trigger list size is [{}]", registrar.getTriggerTaskList().size());

                CronTrigger cronTrigger = new CronTrigger(cron);
                Date nextExecTime = cronTrigger.nextExecutionTime(triggerContext);
                return nextExecTime;
            }
        };
    }

    /**
     * 是否存在任务
     *
     * @param taskId
     * @return
     */
    private boolean hasTask(String taskId) {
        return this.scheduledFutures.containsKey(taskId);
    }

    private void refreshTasks(List<TimingTask> tasks) {
        //取消已经删除的策略任务
        Set<String> taskIds = scheduledFutures.keySet();
        for (String taskId : taskIds) {
            if (!exists(tasks, taskId)) {
                scheduledFutures.get(taskId).cancel(true);
                scheduledFutures.remove(taskId);
                cronTasks.remove(taskId);
            }
        }
        for (TimingTask tt : tasks) {
            String expression = tt.getExpression();
            if (StringUtils.isBlank(expression) || !CronSequenceGenerator.isValidExpression(expression)) {
                logger.error("定时任务DynamicTask cron表达式不合法: " + expression);
                if (hasTask(tt.getTaskId())) {
                    scheduledFutures.get(tt.getTaskId()).cancel(true);
                    scheduledFutures.remove(tt.getTaskId());
                    cronTasks.remove(tt.getTaskId());
                }
                continue;
            }
            //如果配置一致，则不需要重新创建定时任务
            if (scheduledFutures.containsKey(tt.getTaskId())
                    && cronTasks.get(tt.getTaskId()).getExpression().equals(expression)) {
                continue;
            }
            //如果策略执行时间发生了变化，则取消当前策略的任务
            if (scheduledFutures.containsKey(tt.getTaskId())) {
                scheduledFutures.get(tt.getTaskId()).cancel(true);
                scheduledFutures.remove(tt.getTaskId());
                cronTasks.remove(tt.getTaskId());
            }
            CronTask task = new CronTask(tt, expression);
            ScheduledFuture<?> future = registrar.getScheduler().schedule(task.getRunnable(), getTrigger(expression));
            cronTasks.put(tt.getTaskId(), task);
            scheduledFutures.put(tt.getTaskId(), future);

//            registrar.addTriggerTask(task.getRunnable(), getTrigger(expression));
        }
    }

    private boolean exists(List<TimingTask> tasks, String taskId) {
        for (TimingTask task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return true;
            }
        }
        return false;
    }

    @PreDestroy
    public void destroy() {
        this.registrar.destroy();
    }

    public static class TaskConstant {
        private String cron;
        private String taskId;

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }
    }

    /**
     * 队列消费线程工厂类
     */
    private static class DynamicTaskConsumeThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DynamicTaskConsumeThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-dynamic-task-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    private static class DynamicBlockingQueue extends LinkedBlockingQueue<String> {
        private volatile boolean done = false;


        DynamicBlockingQueue(int capacity) {
            super(capacity);
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }
    }

    private class TimingTask implements Runnable {
        private String expression;

        private String taskId;

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            //设置队列大小10
            logger.error("当前CronTask: " + this);
            DynamicBlockingQueue queue = new DynamicBlockingQueue(3);
            es.submit(() -> {
                while (!queue.isDone() || !queue.isEmpty()) {
                    try {
                        String content = queue.poll(500, TimeUnit.MILLISECONDS);
                        if (StringUtils.isBlank(content)) {
                            return;
                        }
                        logger.info("DynamicBlockingQueue 消费：" + content);
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            //队列放入数据
            for (int i = 0; i < 5; ++i) {
                try {
                    queue.put(String.valueOf(i));
                    logger.info("DynamicBlockingQueue 生产：" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.setDone(true);


        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this
                    , ToStringStyle.DEFAULT_STYLE
                    , false
                    , false
                    , TimingTask.class);
        }

    }
}

