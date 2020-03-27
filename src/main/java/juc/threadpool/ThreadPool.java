package juc.threadpool;

import java.util.concurrent.*;

/**
 * 测试连接池
 *
 * @Author: 李昭
 * @Date: 2020/3/20 16:21
 */
public class ThreadPool {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        ThreadPoolDemo demo = new ThreadPoolDemo();
        CallableDemo callableDemo = new CallableDemo();
        for (int i = 0; i < 10; ++i) {
            service.submit(demo);
            Future<Integer> submit = service.submit(callableDemo);
            try {
                Integer integer = submit.get();
                System.out.println(Thread.currentThread().getName()+":"+integer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }
}

class ThreadPoolDemo implements Runnable {

    private int i = 0;
    @Override
    public void run() {
        while (i < 100) {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}
class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; ++i) {
            sum += i;
        }
        return sum;
    }
}