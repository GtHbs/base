package juc.thread;

/**
 * @Author: 李昭
 * @Date: 2020/3/22 21:54
 */
public class MyThread {
    public static void main(String[] args) {
        TestThread thread = new TestThread();
        TestThread thread2 = new TestThread();
        thread.setPriority(10);
        thread2.setPriority(1);
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        thread2.start();
    }
}
class TestThread extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 5; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}
