package juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁: 在完成某些运算时,只有其他所有的运算全部完成,当前运算才会继续执行
 *
 * @Author: 李昭
 * @Date: 2020/3/19 17:10
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        /**
         * 一个计数器,每次只能有一个线程操作
         */
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo demo = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; ++i) {
            new Thread(demo).start();
        }
        try {
            //该方法只有latch的值为0时才继续执行,有阻塞的作用
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间为:" + (end - start));
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(final CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; ++i) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();
            }
        }
    }
}