package com.deschen.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Author deschen
 * @Create 2019/6/1
 * @Description 多线程执行定时任务
 * @Since 1.0.0
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个长度5的定时任务线程池
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
    }
}
