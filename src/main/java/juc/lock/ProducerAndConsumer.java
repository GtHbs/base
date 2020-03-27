package juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 *
 * @Author: 李昭
 * @Date: 2020/3/22 21:22
 */
@SuppressWarnings("all")
public class ProducerAndConsumer {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Product product = new Product();
        Producter producter = new Producter(product);
        Consume consume = new Consume(product);
        new Thread(producter, "A").start();
        new Thread(consume, "B").start();
        new Thread(consume, "C").start();
    }
}

class Product {
    private int product = 10;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition produceCondition = lock.newCondition();
    private Condition saleCondition = lock.newCondition();

    public void produce() {
        synchronized (this) {
            try {
                if (product >= 10) {
                    System.out.println("库存已满");
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + ":" + product++);
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sale() {
        synchronized (this) {
            try {
                if (product <= 0) {
                    System.out.println("库存不足");
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + ":" + product--);
                notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Producter implements Runnable {


    private Product product;

    public Producter(final Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            product.produce();
        }
    }
}

class Consume implements Runnable {

    private Product product;

    public Consume(final Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            product.sale();
        }
    }
}

