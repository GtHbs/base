package juc.lock;

/**
 * 线程八锁
 * 1,两个普通同步方法,两个线程标准打印:one two
 * 2,给one方法中添加线程睡眠,打印:one two
 * 3,给monitor类中添加非同步方法,打印:three one two
 * 4,两个对象,一个调用one,一个调用two,打印:two one
 * 5,one方法为静态方法,打印:two one
 * 6,修改两个方法都为静态同步方法,打印: one two
 * 7,两个对象,一个调用静态同步方法one,另一个调用普通同步方法two,打印:two one
 * 8,两个静态同步方法,两个对象打印:one two
 *
 * 总结:
 *      非静态方法:无锁
 *      非静态同步方法:对象锁
 *      静态同步方法:类锁
 *      静态方法:无锁
 * 对于类锁,同一时刻只能有一个对象获得
 * 对于对象锁,同一时刻只能有一个对象获得
 *  @Author: 李昭
 * @Date: 2020/3/20 15:58
 */
@SuppressWarnings("all")
public class TestThreadMonitor {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        Monitor monitor1 = new Monitor();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.one();
            }
        });
//        thread.setPriority(1);
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor1.two();
            }
        });
//        thread1.setPriority(10);
        thread1.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                monitor.three();
//            }
//        }).start();
    }
}

@SuppressWarnings("all")
class Monitor {

    public static synchronized void one() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("one");
    }

    public static synchronized void two() {
        System.out.println("two");
    }

    public void three() {
        System.out.println("three");
    }
}
