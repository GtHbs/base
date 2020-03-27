package juc.memoryvisibility;

/**
 * volatile关键字:当多个线程操作共享数据时,被volatile修饰的关键字会实时刷新,
 * 也就是main线程直接操作主存中的数据,而不是先将数据读到缓存中再操作
 *
 * @author: 李昭
 * @Date: 2020/3/19 15:22
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        /**
         * 内存可见性问题:
         *      Java内存模型分为主内存和工作内存,每个线程都有一块工作内存,
         *      每次工作时只操作工作内存中的数据,使用完后再将其更新到主内存中,
         *      这就导致了其他线程修改了共享变量而该线程不可见的问题
         * 解决内存可见性:
         *      使用锁:对共享数据的操作只能一个一个来,不能将其复制到工作内存中
         *      volatile:可以实现可见性,但是不具备原子性和互斥性
         *      实现原理:
         *           对volatile变量进行写操作时,会在写操作后加入一条store屏障指令(将工作内存的值更新到主内存中)
         *           对volatile变量进行读操作时,会在读操作前加入一条load屏障指令(强迫从主内存中读取值)
         *
         */
        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {


    private volatile Boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("falg=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public Boolean getFlag() {
        return this.flag;
    }

    public void setFlag(final Boolean flag) {
        this.flag = flag;
    }
}
