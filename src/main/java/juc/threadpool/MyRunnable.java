package juc.threadpool;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 4:26 PM
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(final String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "start Time: " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + "end Time: " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}

class ThreadPollExecutorDemo {
    /**
     * 核心运行线程数
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 10;
    /**
     * 等待队列,当核心线程数已满的时候新来的线程会加入到该队列
     */
    private static final int QUEUE_CAPACITY = 100;
    /**
     * 存活时间
     */
    private static final Long KEEP_ALLIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEP_ALLIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; ++i) {
            Runnable runnable = new MyRunnable("" + i);
            executor.execute(runnable);
        }
        /**
         * 关闭线程池,不再接收新线程,直到队列中的所有线程执行完
         */
        executor.shutdown();
        /**
         * 终止所有的任务,将队列中的任务返回
         */
        executor.shutdownNow();
        /**
         * 所有任务都执行完后返回true
         */
        while (!executor.isTerminated()) {

        }
        System.out.println("finish all");
    }
}