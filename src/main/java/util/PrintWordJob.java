package util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * 具体的执行方法
 *
 * @author: 李昭
 * @Date: 2020/4/1 9:06
 */
public class PrintWordJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalTime localTime = now.toLocalTime();
        System.out.println("PrintTime: "+localTime + "PrintWord: "+ UUID.randomUUID());
    }
}
