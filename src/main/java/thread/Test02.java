package thread;

/**
 * @author 李昭
 */
public class Test02 implements Runnable {
    Timer timer = new Timer();

    public static void main(String[] args) {
        Test02 t = new Test02();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.setName("Thread1");
        t2.setName("Thread2");
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        timer.add(Thread.currentThread().getName());
    }
}

class Timer {
    private static int num = 0;

    //在执行当前方法时,当前对象被锁定
    public synchronized void add(String name) {
        ++num;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "第" + num + "个使用该线程!!");
    }
}

/**
 * 测试死锁
 */
class TestDeadLock implements Runnable {

    public int flag = 1;
    static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }

    public static void main(String[] args) {
        TestDeadLock l1 = new TestDeadLock();
        TestDeadLock l2 = new TestDeadLock();
        l1.flag = 1;
        l2.flag = 0;
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();
    }
}

class T implements Runnable {
    int b = 100;

    /**
     * 锁只针对当前线程
     *
     * @throws Exception
     */
    public synchronized void m() throws Exception {
        System.out.println("1");
        b = 1000;
        Thread.sleep(5000);
        System.out.println(b + ";");
    }

    public synchronized void n() throws Exception {
        System.out.println("2");
        Thread.sleep(2500);
        b = 2000;
        System.out.println(b + "x");
    }

    public static void main(String[] args) throws Exception {
        /**
         * 在主线程内,其他线程的启动是随机的(没有阻塞)
         */
        T t = new T();
        Thread thread = new Thread(t);
        thread.start();
        Thread.sleep(1000);
        t.n();
        System.out.println(t.b + ":");
    }

    @Override
    public void run() {
        try {
            m();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}