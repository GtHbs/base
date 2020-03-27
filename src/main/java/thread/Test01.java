package thread;

import java.util.Date;

/**
 * @author 李昭
 */
public class Test01 {
    public static void main(String[] args) {
        th3 t1 = new th3();
        th3 t2 = new th3();
        Thread.State state = t1.getState();     //获取线程状态
        t1.setPriority(Thread.NORM_PRIORITY + 5);
        t1.start();
        t2.start();
        t1.setDaemon(true);     //将用户线程调整为守护线程,虚拟机不需要等待其执行完
        int i = Thread.activeCount();       //活动的线程数
        System.out.println("K:"+i);
    }
}
class Runner implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner:"+i);
        }
    }
}
class th1 extends Thread {
    boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            System.out.println("----"+new Date()+"-------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

class th2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("th2:"+getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

class th3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i){
            System.out.println(getName() +":"+ i);
            if ((i % 10) == 0) {
                yield();        //暂停当前线程,但是不需要唤醒
            }
        }
    }
}



