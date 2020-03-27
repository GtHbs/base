package juc.concurrentcollection;

/**
 * 使用线程安全的Map:
 *      1,Collection.SynchronizedMap(new HashMap());
 *          不推荐使用,因为这个是使用全局的锁来同步
 *      2,ConcurrentHashMap:
 *          读的时候几乎不需要加锁,写的时候采用分段锁的机制,提高了并发效率
 *
 *
 * @Author: 李昭
 * @Date: 3/24/2020 4:51 PM
 */
public class ConcurrentHashMapDemo {
}
