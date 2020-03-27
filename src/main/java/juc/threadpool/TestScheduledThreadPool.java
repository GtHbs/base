package juc.threadpool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: 李昭
 * @Date: 2020/3/20 18:53
 */
public class TestScheduledThreadPool {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; ++i) {
            ScheduledFuture<Integer> schedule = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(1000);
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);
            Integer num = schedule.get();
            System.out.println(num);
        }
        pool.shutdown();
    }
}

