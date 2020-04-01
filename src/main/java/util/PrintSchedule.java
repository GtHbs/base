package util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 调度器
 *
 * @author: 李昭
 * @Date: 2020/4/1 9:10
 */
public class PrintSchedule {

    public static void main(String[] args) throws Exception {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail detail = JobBuilder.newJob(PrintWordJob.class).withIdentity("ss", "dd").build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "trigger").
                startNow().
                withSchedule(SimpleScheduleBuilder.
                        simpleSchedule().
                        withIntervalInSeconds(1).
                        repeatForever()).build();
        scheduler.scheduleJob(detail, trigger);
        scheduler.start();
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
    }

    /**
     * 在指定的时间执行任务
     *
     * @param date
     * @throws SchedulerException
     */
    public void test(Date date) throws SchedulerException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordJob.class).build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("* 30 10 ? * 1/5 2018")).build();
        //4、执行
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }
}
