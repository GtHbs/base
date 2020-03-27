package juc.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程计时器
 *
 * @author: 李昭
 * @Date: 3/24/2020 12:16 PM
 */
@SuppressWarnings("all")
public class CountDownLatchTest {

    private static final int threadCount = 50;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(150);
        final CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; ++i) {
            final int number = i;
            pool.execute(() -> {
                try {
                    test(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        pool.shutdown();
        System.out.println("finish");
    }


    public static void test(int number) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("thread number:" + number);
        Thread.sleep(1000);
    }
}
