//package com.ruoyi.project.quartz.controller;
//
//import com.ruoyi.framework.web.domain.AjaxResult;
//import com.ruoyi.project.quartz.job.PushMessageJob;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
///**
// * @author by hujun
// * @date 2022-12-13
// */
//
//@RestController
//@RequestMapping("/push")
//@Api(tags = "推送消息定时任务")
//public class PushMessageController {
//
//    @ApiOperation("测试定时推送消息")
//    @GetMapping("/test")
//
//    public AjaxResult testPush() throws SchedulerException {
//        // 1、创建 scheduled 工厂
//        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
//        // 2、从工厂中获得调度器的实例
//        Scheduler scheduler = stdSchedulerFactory.getScheduler();
//
//        // 3、创建 JobDetail，引用 “job” 具体的执行类
//        JobDetail job = JobBuilder.newJob(PushMessageJob.class)
//                .withDescription("xiangjiao  test") // job描述
//                .withIdentity("xiangjiao", "bunana") // name 和 group
//                .build();
//
//        // 任务执行时间，触发器等
//        long startTimes = System.currentTimeMillis()+3*1000L;
//        Date startDate = new Date(startTimes);
//
//        // 4、创建 CronTrigger
//        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//                .withDescription("this is a cronTrigger")
//                .withIdentity("xiangjiao2", "test2")
//                .startAt(startDate)
//                // 使用 SimpleScheduleBuilder 或者  CronScheduleBuilder
//                //.withSchedule(SimpleScheduleBuilder.simpleSchedule())
//                //.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) // 每5秒执行一次(正常)
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")) // 每5秒执行一次(异常)
//                .build();
//
//        // 5、注册任务和定时器
//        scheduler.scheduleJob(job,cronTrigger);
//
//        // 6、启动 定时调度器
//        scheduler.start();
//
//        // 7、关闭调度器
//        // scheduler.shutdown();
//
//        return AjaxResult.success();
//    }
//}
