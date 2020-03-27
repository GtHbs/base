package thread.synchronize;

/**
 * @author 李昭
 */
public class UnSafe {
    public static void main(String[] args) {
        ticket t3 = new ticket();
        Thread thread01 = new Thread(t3,"th1");
        Thread thread02 = new Thread(t3,"th2");
        Thread thread03 = new Thread(t3,"th3");
        thread01.start();
        thread02.start();
        thread03.start();

    }
}
class ticket implements Runnable {

    private int ticket = 10;
    private boolean flag = true;
    @Override
    public synchronized void run() {
        while (flag) {
            test();
        }
    }
    private void test() {
        if (ticket < 0){
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName() +":"+ticket--);
    }
}
