package thread.synchronize;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁:锁可以延续使用
 *
 * @author 李昭
 */
public class LockTest {
    ReLock lock = new ReLock();
    ReentrantLock reentrantLock = new ReentrantLock();  //可重入锁

    public void a() throws InterruptedException {
        reentrantLock.lock();
        lock.lock();        //上锁
        System.out.println(lock.getCount());
        System.out.println("re:" + reentrantLock.getHoldCount());
        b();
        lock.unLock();      //重复释放
        reentrantLock.unlock();
    }

    //不可重入锁
    public void b() throws InterruptedException {
        reentrantLock.lock();
        lock.lock();        //继承自上面的锁
        lock.unLock();      //释放锁
        reentrantLock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest test = new LockTest();
        test.a();
        test.b();
    }

    public void test() {
        synchronized (this) {               //第一次获得锁
            while (true) {
                synchronized (this) {       //第二次获得锁
                    System.out.println("ReetrantLock!");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 不可重入锁
 */
class Lock {
    //是否被占用
    private boolean isLocked = false;

    //不可重入锁
    public void lock() {
        while (isLocked) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    public synchronized void unLock() {
        isLocked = false;
        notify();
    }
}

/**
 * 可重入锁
 */
class ReLock {
    //是否被占用
    private boolean isLocked = false;
    Thread current = null;      //存储线程
    private int count = 0;

    public int getCount() {
        return count;
    }

    //可重入锁
    public void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && current != thread) {
            this.wait();
        }
        isLocked = true;
        current = thread;
        ++count;
    }

    public synchronized void unLock() {
        if (Thread.currentThread() == current) {
            --count;
            if (count == 0) {
                isLocked = false;
                notify();
                current = null;
            }
        }

    }
}