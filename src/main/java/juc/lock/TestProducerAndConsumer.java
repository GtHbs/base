package juc.lock;

/**
 * 生产者和消费者案例
 *
 * @Author: 李昭
 * @Date: 2020/3/19 18:01
 */
@SuppressWarnings("all")
public class TestProducerAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(producer, "生产者A").start();
        new Thread(consumer, "消费者B").start();
        new Thread(producer, "生产者C").start();
        new Thread(consumer, "消费者D").start();
    }
}

/**
 * 店员类
 *
 * @author 李昭
 */
class Clerk {

    private int product = 0;

    public synchronized void get() {
        while (product >= 1) {
            System.out.println("库存已满!");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + ++product);
        this.notifyAll();
    }

    public synchronized void sale() {
        /**
         * 为了避免虚假唤醒,必须将wait放在循环中执行
         */
        while (product <= 0) {
            System.out.println("库存为空");
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + ":" + --product);
    }
}

/**
 * 生产者
 *
 * @author 李昭
 */
class Producer implements Runnable {

    private Clerk clerk;

    public Producer(final Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            clerk.get();
        }
    }
}

/**
 * 消费者
 *
 * @author 李昭
 */
class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(final Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            clerk.sale();
        }
    }
}