package juc.memoryvisibility;

/**
 * @Author: 李昭
 * @Date: 2020/3/19 16:11
 */
public class VolatileDemo {
    private volatile int number = 0;

    public int getNumber() {
        return this.number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public void increase() {
        this.number++;
    }

    public static void main(String[] args) {
        VolatileDemo demo = new VolatileDemo();
        for (int i = 0; i < 20; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.increase();
                }
            }).start();
        }

        /**
         * 判断是否还有子线程在运行,如果有则让主线程让出线程资源,直到所有的子线程运行完再执行
         */
        while (Thread.activeCount() > 0) {
            Thread.yield();
        }
        System.out.println("number="+demo.getNumber());
    }
}
