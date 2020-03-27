package juc.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: 李昭
 * @Date: 2020/3/22 20:53
 */
@SuppressWarnings("all")
public class ConditionPrint {
    public static void main(String[] args) {
        Print print = new Print();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    print.printA();
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    print.printB();
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    print.printC();
                }
            }
        });
    }
}

@SuppressWarnings("all")
class Print {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            if (number != 1) {
                conditionA.await();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + ":" + "A");
            number = 2;
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            if (number != 2) {
                conditionB.await();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+":"+"B");
            number = 3;
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            if (number != 3) {
                conditionC.await();
            }
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+":"+"C");
            number = 1;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
