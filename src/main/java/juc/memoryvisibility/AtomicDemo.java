package juc.memoryvisibility;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 李昭
 * @Date: 2020/3/19 16:23
 */
public class AtomicDemo {
    public static void main(String[] args) {
        Atomic atmoic = new Atomic();
        for (int i = 0; i < 10; ++i) {
            new Thread(atmoic).start();
        }
    }
}

class Atomic implements Runnable {

    /**
     * 仅使用volatile不能保证变量的原子性,可以使用java.util.concurrent.atomic包下的变量来保证原子性
     *  1,volatile保证可见性
     *  2,cas(compare and swap)保证原子性
     *      (1) 内存值 V
     *      (2) 预估值 A
     *      (3) 更新值 B
     *      当且仅当V == A V = B,否则不做任何操作
     */
    //private volatile Integer serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public Integer getSerialNumber() {
        return this.serialNumber.getAndIncrement();
    }

}