package juc.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量可以使多个线程同时访问统一资源
 *
 * @Author: 李昭
 * @Date: 3/24/2020 12:01 PM
 */
@SuppressWarnings("all")
public class SemaphoreExample {

    /**
     * 请求的数量
     */
    private static final int threadCount = 50;

    public static void main(String[] args) {
        /**
         * 创建固定大小的线程池
         */
        ExecutorService pool = Executors.newFixedThreadPool(30);
        /**
         * 一次只能允许执行的线程数量
         * 信号量有两种模式:
         *  1,公平模式:调用acquire的顺序就是获取许可证的顺序,遵循fifo
         *  2,非公平模式:抢占式的
         */
        Semaphore semaphore = new Semaphore(20);
        for (int i = 0; i < threadCount; ++i) {
            final int threadNumber = i;
            pool.execute(() -> {
                try {
                    /**
                     * 该方法会阻塞,直到有一个许可证可以获得并且拿走这个许可证
                     * 可以在require中指定获取许可的数量,数字越大,每次执行的线程越少
                     * 比如参数为5,一共20个,20 / 5 = 4,每次只能执行4个线程
                     */
                    semaphore.acquire(5);
                    test(threadNumber);
                    /**
                     * 会增加一个许可证,会释放掉一个阻塞的require
                     */
                    semaphore.release(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadNumber) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Thread Number:" + threadNumber);
        Thread.sleep(1000);
    }
}
