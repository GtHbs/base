package juc.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList/CopyOnWriteArraySet:写入并赋值
 *
 * @Author: 李昭
 * @Date: 2020/3/19 17:03
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        final HelloThread helloThread = new HelloThread();
        for (int i = 0; i < 10; ++i) {
            new Thread(helloThread).start();
        }
    }
}

class HelloThread implements Runnable {

    /**
     * 可以使用包装类将其包装为线程安全的类(仅限于读),
     * 一边读取一边插入会导致并发异常
     */
//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());
    /**
     * 每次添加都会将底层线性表复制一份,会导致效率非常低下
     */
    private static List<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
    }
    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add(UUID.randomUUID().toString());
        }
    }
}
