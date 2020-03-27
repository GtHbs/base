package time;

import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @author: 李昭
 * @Date: 3/24/2020 8:45 PM
 */
public class TimeTest {

    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间:" + now);
        //获得年月日
        LocalDate localDate = now.toLocalDate();
        System.out.println("LocalDate:" + localDate);
        //获得时分秒
        LocalTime localTime = now.toLocalTime();
        System.out.println("LocalTime:" + localTime);
        //月
        Month month = now.getMonth();
        //天
        int day = now.getDayOfMonth();
        //秒
        int second = now.getSecond();
        System.out.println("Month:" + month + ",day:" + day + ",second:" + second);

        //指定固定时间
        LocalDateTime localDateTime = now.withDayOfMonth(10).withYear(2012);
        System.out.println("Local Date Time:" + localDateTime);

        LocalDate date = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("Fixed date:" + date);

        LocalTime time = LocalTime.of(22, 15);
        System.out.println("Fixed time:" + time);

        LocalTime parse = LocalTime.parse("20:15:30");
        System.out.println(parse);
    }

    @Test
    public void testZonedDateTime() {
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当期时区: " + currentZone);
    }
}
