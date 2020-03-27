package juc.aqs;

/**
 * AQS(AbstractQueuedSynchronizer):
 *      原理:如果被请求的共享资源空闲,则将当前请求线程设置为有效的工作线程,并且将共享资源
 *      设置为锁定状态.如果共享资源被锁定,那么将暂时不能获得锁的线程加入到队列中
 *      将每一个请求的线程封装成一个CLH锁队列的一个结点来分配锁
 * ReentrantLock:使用非公平锁
 *      公平锁:安装线程在队列中排队,先到者先拿锁
 *      非公平锁:先通过两次cas操作去拿锁,没有拿到,将线程加入到队列中等待唤醒
 *
 * @Author: 李昭
 * @Date: 3/24/2020 11:48 AM
 */
public class TestAQS {

}
