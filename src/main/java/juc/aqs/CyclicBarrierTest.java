package juc.aqs;

import java.util.concurrent.*;

/**
 * 循环栅栏使用:
 * 一组线程到达一个屏障时阻塞,直到最后一个线程到达时,屏障才会打开门,之前所有被拦截的线程才会继续运行
 *
 * @Author: 李昭
 * @Date: 3/24/2020 12:22 PM
 */
@SuppressWarnings("all")
public class CyclicBarrierTest {
    private static final int threadCount = 50;
    /**
     * 需要同步的线程数量
     */
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5, () -> {
        System.out.println("当线程数到达之后,优先执行");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadCount; ++i) {
            final int number = i;
            Thread.sleep(100);
            pool.execute(() -> {
                try {
                    test(number);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }

    public static void test(int threadNumber) {
        System.out.println("Thread Number:" + threadNumber + " is ready");
        try {
            /**
             * 等待60秒,确保子线程全部完成
             */
            CYCLIC_BARRIER.await(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Cyclic Barriar Exception");
        }
        System.out.println("Thread Number:" + threadNumber + " is finish");
    }
}
