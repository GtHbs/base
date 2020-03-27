package basicClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author 李昭
 */
public class TestDate {

    public static void main(String[] args) throws ParseException {
        char chr = 127;
        int sum = 200;
        chr += 1;
        sum += chr;
        System.out.println(sum);
    }


    public static void testDate()
    {
        Date date = new Date(System.currentTimeMillis());
        Date date1 = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(format.format(date));
        Date now = null;
        try {
            now = format.parse("2019-11-17 22:22:22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(now);
        /**
         * D:年中的天数
         * d:月份中的天数
         * F:月份中的星期
         * E:星期中的天数
         */
        DateFormat f2 = new SimpleDateFormat("E");
        System.out.println(f2.format(date));
    }

    /**
     * 日期测试类
     */
    public static void testCalendar()
    {
        //日期的获取
        Calendar calendar = new GregorianCalendar(2020,1,16);
        System.out.println(calendar.get(Calendar.YEAR)+"\t"+calendar.get(Calendar.MONTH)+"\t"+calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //日期的设置
        Calendar c2 = new GregorianCalendar();
        c2.set(Calendar.YEAR,2020);
        c2.set(Calendar.MONTH,1);
        System.out.println(c2);
        //日期的设置
        Calendar c3 = new GregorianCalendar();
        c3.add(Calendar.DATE,20);
        System.out.println(c3);
        //日期对象和事件对象的转换
        Date c3Time = c3.getTime();
        Calendar c4 = new GregorianCalendar();
        c4.setTime(new Date());
    }

    /**
     * 可视化日历
     */
    public static void visualizeCalendar()
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("请输入日期:\t\t\t(格式2019-01-02)");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            Date date = format.parse(line);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DAY_OF_MONTH);                  //本月第几天
            System.out.println("一\t\t二\t\t三\t\t四\t\t五\t\t六\t\t日");      //采用中国的那一套
            calendar.set(Calendar.DAY_OF_MONTH,1);                          //每月从1号开始
            for (int i = 0; i < calendar.get(Calendar.DAY_OF_WEEK) - 2;++i) //中国日历是从周一开始,因此要减2
                System.out.print("\t\t");
            int maximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); //本月最大天数
            for (int i = 1; i <= maximum; ++i)
            {
                System.out.print(calendar.get(Calendar.DAY_OF_MONTH));
                if (calendar.get(Calendar.DAY_OF_MONTH) == day)
                    System.out.print("*");      //当天时间后面加上*
                System.out.print("\t\t");
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)  //周日换行
                    System.out.println();
                calendar.add(Calendar.DAY_OF_MONTH,1);               //天数加1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




















}
