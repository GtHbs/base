package juc.concurrentcollection;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用阻塞队列实现生产者消费者模式
 *
 * @Author: 李昭
 * @Date: 3/24/2020 5:07 PM
 */
public class ArrayBlockQueueDemo {
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

    public static void main(String[] args) {
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}
class Producer implements Runnable {

    private final BlockingQueue queue;

    public Producer(final BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!queue.isEmpty()) {
            int i = random.nextInt(1000000);
            System.out.println("Produce: "+i);
            try {
                queue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable {

    private BlockingQueue queue;

    public Consumer(final BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consume: "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
