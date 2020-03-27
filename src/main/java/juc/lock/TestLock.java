package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决多线程同步问题:
 * 1,同步块和同步方法
 * 2,锁
 *
 * @Author: 李昭
 * @Date: 2020/3/19 17:46
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "一号窗口").start();
        new Thread(ticket, "二号窗口").start();
        new Thread(ticket, "三号窗口").start();
    }
}

class Ticket implements Runnable {

    private Integer tick = 100;

    private Lock lock = new ReentrantLock();

    private boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            lock.lock();
            try {
                if (tick > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + --tick);
                } else {
                    flag = false;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}