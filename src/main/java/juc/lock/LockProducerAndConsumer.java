package juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 李昭
 * @Date: 2020/3/20 15:22
 */
@SuppressWarnings("all")
public class LockProducerAndConsumer {
    public static void main(String[] args) {
        LockClerk clerk = new LockClerk();
        LockConsumer consumer = new LockConsumer(clerk);
        LockProducer producer = new LockProducer(clerk);
        new Thread(consumer, "consumer-1").start();
        new Thread(consumer, "consumer-2").start();
        new Thread(producer, "producer-1").start();
        new Thread(producer, "producer-2").start();
    }
}

class LockClerk {

    private Integer product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void get() {
        lock.lock();
        try {
            while (product >= 10) {
                System.out.println("库存已满!");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("库存为空!");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class LockProducer implements Runnable {

    private LockClerk clerk;

    public LockProducer(final LockClerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            clerk.get();
        }
    }
}

class LockConsumer implements Runnable {

    private LockClerk clerk;

    public LockConsumer(final LockClerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            clerk.sale();
        }
    }
}

