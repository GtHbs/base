package juc.lock;

/**
 * 可重入锁:即一个线程获取可当前锁,而锁内的该锁可以重复获得
 *
 * @Author: 李昭
 * @Date: 2020/3/20 19:59
 */
public class TestReetrantLock {

    public void test() {
        synchronized (this) {
            boolean flag = true;
            while (flag) {
                synchronized (this) {
                    int count = 1;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                    ++count;
                    System.out.println("reetrant lock");
                    if (count >= 10) {
                        flag = false;
                    }
                }
            }
        }
    }

    ReLock lock = new ReLock();

    public void a() throws Exception {
        lock.lock();
        System.out.println(lock.getHoldCount());
        b();
        lock.unlock();
    }

    //不可重入锁
    public void b() throws Exception {
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
    }

    public static void main(String[] args) throws Exception {
        TestReetrantLock lock = new TestReetrantLock();
        lock.a();
    }
}

/**
 * 不可重入锁
 */
class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws Exception {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}

/**
 * 可重入锁
 */
class ReLock {


    private boolean isLocked = false;

    //获得锁的线程
    Thread lockedBy = null;
    //线程数
    private Integer holdCount = 0;

    public Integer getHoldCount() {
        return this.holdCount;
    }

    public synchronized void lock() throws Exception {
        /**
         * 锁已经被获得并且获得锁的线程不是当前线程则等待
         */
        while (isLocked && lockedBy != Thread.currentThread()) {
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        holdCount++;
    }

    /**
     * 解锁
     */
    public synchronized void unlock() {
        /**
         * 锁是被当前线程获得
         */
        if (Thread.currentThread() == lockedBy) {
            holdCount--;
            /**
             * 线程为0清除锁
             */
            if (holdCount == 0) {
                isLocked = false;
                notify();
                lockedBy = null;
            }
        }
    }
}