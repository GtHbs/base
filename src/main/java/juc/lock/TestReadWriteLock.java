package juc.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁:使用情形 写写/读写
 *
 * @Author: 李昭
 * @Date: 2020/3/20 15:48
 */
@SuppressWarnings("all")
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.set(1000);
            }
        }, "write:").start();
        for (int i = 0; i < 100; ++i) {
            demo.get();
        }
    }
}

@SuppressWarnings("all")
class ReadWriteLockDemo {

    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void set(int number) {
        lock.writeLock().lock();
        try {
            this.number = number;
            System.out.println(Thread.currentThread().getName()+":"+number);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
