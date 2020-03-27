package thread.others;

/**
 * ThreadLocal就是每个线程自身存储区域
 * 归属:
 * 1,构造器:那里调用属于哪里(找线程体)
 * 2,其他方法:属于本线程
 *
 * @author 李昭
 */
public class ThreadLocal01 {
    /*
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return 1;
        }
    };
    */
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 200);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        threadLocal.set(2000);
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        Thread thread = new Thread(new Runs());
        thread.start();
        Runs runs = new Runs();
        runs.test();
    }

    public static class Runs implements Runnable {
        @Override
        public void run() {
            System.out.println("Runs:"+Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set(threadLocal.get() - 1);
            System.out.println("Runs:"+Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        public Runs() {
            //主线程调用,属于主线程
            System.out.println("constructor:" + Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        /**
         * 主线程调用,线程为主线程
         */
        public void test() {
            System.out.println("test:" + Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }
}
