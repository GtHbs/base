package juc.memoryvisibility;

/**
 * 模拟cas算法
 *
 * @Author: 李昭
 * @Date: 2020/3/19 16:47
 */
public class SimulateCAS {


    private int value;

    public synchronized int get() {
        return value;
    }

    /**
     * 设置值
     *
     * @param expectValue :预估值
     * @param newValue    :更新值
     * @return
     */
    public synchronized int compareAndSwap(int expectValue,int newValue) {
        int oldValue = value;
        if (oldValue == expectValue) {
            this.value = newValue;
        }
        return oldValue;
    }


    public synchronized boolean compareAndSet(int expectValue,int newValue) {
        return expectValue == compareAndSwap(expectValue,newValue);
    }

    public static void main(String[] args) {
        final SimulateCAS cas = new SimulateCAS();
        for (int i = 0; i < 10; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    boolean f = cas.compareAndSet(expectedValue,(int)(Math.random() * 100));
                    System.out.println(f);
                }
            }).start();
        }
    }

}
