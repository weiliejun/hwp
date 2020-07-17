package com.hwp.admin.quarz;

import com.hwp.admin.app.event.RwtxSendEmailEvent;
import com.hwp.admin.app.service.xmrwxx.XmrwxxService;
import com.hwp.admin.app.service.xmxxgl.XmxxglService;
import com.hwp.common.model.xmrwxx.bean.Xmrwxx;
import com.hwp.common.model.xmxxgl.bean.Xmxxgl;
import com.hwp.common.util.BeanHelper;
import com.hwp.common.util.DateHelper;
import com.hwp.common.util.StringHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

//@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableAsync(
//        mode = AdviceMode.PROXY, proxyTargetClass = false,
//        order = Ordered.HIGHEST_PRECEDENCE
//)
public class DynamicCronSchedule implements SchedulingConfigurer {
    private static Logger logger = LoggerFactory.getLogger(DynamicCronSchedule.class);
    @Autowired
    private XmxxglService xmxxglService;
    @Autowired
    private XmrwxxService xmrwxxService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 创建ThreadPoolTaskScheduler线程池
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(8);
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

    private Map<String, Object> taskMap = new LinkedHashMap<String, Object>();
    private final String FIELD_SCHEDULED_FUTURES = "scheduledFutures";
    private ScheduledTaskRegistrar taskRegistrar;
    private Set<ScheduledFuture<?>> scheduledFutures = new HashSet<ScheduledFuture<?>>();
    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    public Map<String, Object> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, Object> taskMap) {
        this.taskMap = taskMap;
    }

    @SuppressWarnings("unchecked")
    private Set<ScheduledFuture<?>> getScheduledFutures()
    {
        if (scheduledFutures == null)
        {
            try
            {
                scheduledFutures = (Set<ScheduledFuture<?>>) BeanHelper.getBeanProperty(taskRegistrar, FIELD_SCHEDULED_FUTURES);
            }
            catch (NoSuchFieldException e)
            {
                throw new SchedulingException("not found scheduledFutures field.");
            }
        }
        return scheduledFutures;
    }

    /**
     * 添加任务
     *
     * @param taskId
     * @param triggerTask
     */
    private void addTriggerTask(String taskId, TriggerTask triggerTask)
    {
        if (taskFutures.containsKey(taskId))
        {
            throw new SchedulingException("the taskId[" + taskId + "] was added.");
        }
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        getScheduledFutures().add(future);
        taskFutures.put(taskId, future);
    }

    /**
     * 取消任务
     *
     * @param taskId
     */
    private void cancelTriggerTask(String taskId)
    {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null)
        {
            future.cancel(true);
        }
        taskFutures.remove(taskId);
        getScheduledFutures().remove(future);
    }

    /**
     * 重置任务
     *
     * @param taskId
     * @param triggerTask
     */
    private void resetTriggerTask(String taskId, TriggerTask triggerTask)
    {
        cancelTriggerTask(taskId);
        addTriggerTask(taskId, triggerTask);
    }

    /**
     * 任务编号
     *
     * @return
     */
    private Set<String> taskIds()
    {
        return taskFutures.keySet();
    }

    /**
     * 是否存在任务
     *
     * @param taskId
     * @return
     */
    private boolean hasTask(String taskId)
    {
        return this.taskFutures.containsKey(taskId);
    }

    /**
     * 任务调度是否已经初始化完成
     *
     * @return
     */
    private boolean inited()
    {
        return this.taskRegistrar != null && this.taskRegistrar.getScheduler() != null;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
        this.taskRegistrar = taskRegistrar;
        

        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("today", DateHelper.getYMDFormatDate(new Date()));
        final List<Xmrwxx> results = (List<Xmrwxx>) xmrwxxService.listXmrwxxByParams(params);
        for (Xmrwxx xmrwxx : results) {
            JSONArray rwxqJson = JSONArray.fromObject(xmrwxx.getRwxq()); // 首先把字符串转成 JSONArray 对象
            //循环子任务
            for (int i = 0; i < rwxqJson.size(); i++) {
                JSONObject job = rwxqJson.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
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
                Xmxxgl xmxxgl = xmxxglService.getXmxxglById(xmrwxx.getXmId());
                String rwmc = job.get("rwmc").toString();
                taskMap.put(rwmc,cron);
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
                        logger.info("cron expression is [{}]", taskMap.get(rwmc));
                        logger.info("trigger list size is [{}]", taskRegistrar.getTriggerTaskList().size());

                        CronTrigger cronTrigger = new CronTrigger(taskMap.get(rwmc).toString());
                        Date nextExecTime = cronTrigger.nextExecutionTime(triggerContext);
                        return nextExecTime;
                    }
                };

                taskRegistrar.addTriggerTask(task, trigger);
            }
        }
    }

    /**
     * 改变定时时间
     *
     * @param taskId
     * @param cron
     */
    public void addOrResetTriggerTask(String taskId, String cron){
try
                {
                    // 等待任务调度初始化完成
                    while (!inited())
                    {
                        Thread.sleep(100);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("任务调度初始化完成，添加任务");
                CronTrigger cronTrigger = new CronTrigger(cron);
                if(!hasTask(taskId)){
//                    addTriggerTask(taskId, new TriggerTask(taskRegistrar.getCronTaskList().getTriggerTaskList().(ScheduledFutureTask)taskFutures.get(taskId),cronTrigger));
                }else{
//                    resetTriggerTask(taskId, new TriggerTask(task,cronTrigger));
                }
    }
}
