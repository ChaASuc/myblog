//package com.deschen.myblog.config;
//
//
//import com.deschen.myblog.core.quartz.LikeTask;
//import com.deschen.myblog.core.quartz.SumLikeTask;
//import org.quartz.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//
//    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";
//
//    private static final String SUM_TASK_IDENTITY = "SumTaskQuartz";
//
//    @Bean
//    public JobDetail quartzDetail(){
//        return JobBuilder.newJob(LikeTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
//    }
//
//    @Bean
//    public JobDetail sumQuartzDetail(){
//        return JobBuilder.newJob(SumLikeTask.class).withIdentity(SUM_TASK_IDENTITY).storeDurably().build();
//    }
//
//    @Bean
//    public Trigger quartzTrigger(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
////                .withIntervalInSeconds(10)  //设置时间周期单位秒
//                .withIntervalInHours(2)  //两个小时执行一次
////                .withIntervalInSeconds(120)
//                .repeatForever();
//        return TriggerBuilder.newTrigger().forJob(quartzDetail())
//                .withIdentity(LIKE_TASK_IDENTITY)
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//
//    @Bean
//    public Trigger sumQuartzTrigger(){
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
////                .withIntervalInSeconds(10)  //设置时间周期单位秒
////                .withIntervalInHours(2)  //两个小时执行一次
////                .withIntervalInSeconds(120)
//                .withIntervalInHours(24)
//                .repeatForever();
//
//        return TriggerBuilder.newTrigger().forJob(sumQuartzDetail())
//                .withIdentity(SUM_TASK_IDENTITY)
//                .withSchedule(scheduleBuilder)
//                .build();
//    }
//}
