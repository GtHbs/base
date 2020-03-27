package juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 线程的另一种创建方式
 *
 * @Author: 李昭
 * @Date: 2020/3/19 17:33
 */
public class TestCallable {

    public static void main(String[] args) throws Exception {
        ThreadDemo demo = new ThreadDemo();
        //运行Callable实现的线程需要使用FutureTask实现
        FutureTask<Integer> task = new FutureTask<>(demo);
        new Thread(task).start();
        Integer result = task.get();
        System.out.println(result);
    }
}
class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; ++i) {
            sum += i;
        }
        return sum;
    }
}