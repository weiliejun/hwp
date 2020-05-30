package com.hwp.admin;


import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration //声明配置类
@EnableAsync //开启异步任务支持
public class TaskExecutorConfig implements AsyncConfigurer {
    private static Logger logger = Logger.getLogger(TaskExecutorConfig.class);

    @Override
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(5);
        // 最大线程数
        taskExecutor.setMaxPoolSize(15);
        // 队列大小
        taskExecutor.setQueueCapacity(100);
        // 线程最大空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 拒绝策略
        taskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
                                                     @Override
                                                     public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                                                         if (!executor.isShutdown()) {
                                                             try {
                                                                 logger.info("start get queue");
                                                                 executor.getQueue().put(r);
                                                                 logger.info("end get queue");
                                                             } catch (InterruptedException e) {
                                                                 logger.error(e.toString(), e);
                                                                 Thread.currentThread().interrupt();
                                                             }
                                                         }
                                                     }
                                                 }
        );
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("my-pool-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     * @author hry
     *
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            logger.info("Exception message - " + throwable.getMessage());
            logger.info("Method name - " + method.getName());
            for (Object param : obj) {
                logger.info("Parameter value - " + param);
            }
        }

    }

}
