package juc.synchronize;

/**
 * 线程安全的单例模式
 *  synchronized和ReentrantLock区别:
 *      两者都是可重入锁:一个线程获得该对象锁时,在不释放锁的情况下可以再次获得该对象的锁
 *      synchronized基于jvm,ReentrantLock基于api
 *      synchronized是非公平锁(抢占式),ReentrantLock为公平锁(FIFO)
 *      synchronized配合Object类的wait,notify,notifyAll方法进行等待/唤醒机制
 *      ReentrantLock使用Condition的await和single,singleAll实现等待/唤醒
 *      区别: wait和await都是阻塞当前线程
 *            notify不一定唤醒当前线程,只会唤醒阻塞中的某一线程
 *            single则是唤醒具体线程
 *
 * @Author: 李昭
 * @Date: 3/24/2020 3:43 PM
 */
@SuppressWarnings("all")
public class Singleton {
    private volatile static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    return new Singleton();
                }
            }
        }
        return instance;
    }
}
