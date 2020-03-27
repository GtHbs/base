package juc.synchronize;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 3:56 PM
 */
@SuppressWarnings("all")
public class VolatileTest {
    private volatile static boolean flag = false;

    public static void main(String[] args) {
        new Thread(()->{
            flag = true;
        }).start();
        while (true) {
            if (flag) {
                System.out.println("Hello");
                break;
            }
        }
        System.out.println("world");
    }
}
