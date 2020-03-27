package juc.memoryvisibility;

/**
 * @Author: 李昭
 * @Date: 2020/3/19 15:54
 */
public class SynchronizedDemo {

    //共享变量
    private Boolean ready = false;
    private Integer result = 0;
    private Integer number = 1;

    public synchronized void write() {
        ready = true;
        number = 2;
    }

    public synchronized void read() {
        if (ready) {
            result = number * 3;
        }
        System.out.println("result=" + result);
    }

    private class ReadWriteThread extends Thread {
        private Boolean flag;

        public ReadWriteThread(final Boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            if (flag) {
                write();
            } else {
                read();
            }
        }
    }

    /**
     * 导致共享变量在线程之间不可见的原因:
     * 1,线程的交叉执行
     * 2,线程的交叉执行结合重排序
     * 3,共享变量更新后的值没有在工作内存和主内存之间及时更新
     *
     * @param args
     */
    public static void main(String[] args) {
        SynchronizedDemo demo = new SynchronizedDemo();
        //result=0 result=6
        demo.new ReadWriteThread(true).start();
        demo.new ReadWriteThread(false).start();
    }
}
