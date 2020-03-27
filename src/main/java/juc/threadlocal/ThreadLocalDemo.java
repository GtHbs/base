package juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal就是存储每个线程的本地变量
 *
 * @Author: 李昭
 * @Date: 3/24/2020 4:04 PM
 */
@SuppressWarnings("all")
public class ThreadLocalDemo {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    static void print(String s) {
        System.out.println(s +":"+LOCAL.get());
        /**
         * 清除缓存(key为弱引用,value为强引用,可能会造成内存泄漏)
         */
        LOCAL.remove();
    }


    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(30);
        pool.execute(()->{
            LOCAL.set(Thread.currentThread().getName());
            print("1");
            System.out.println("After remove: "+LOCAL.get());
        });
        pool.execute(()->{
            LOCAL.set(Thread.currentThread().getName());
            print("2");
            System.out.println("After remove: "+LOCAL.get());
        });
        pool.shutdown();
    }
}
