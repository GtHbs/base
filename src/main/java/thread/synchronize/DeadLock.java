package thread.synchronize;

/**
 * 死锁
 * @author 李昭
 */
public class DeadLock {
    public static void main(String[] args) {
        MakeUp makeUp = new MakeUp(1,"邓思萱");
        MakeUp makeUp2 = new MakeUp(0,"李洁");
        makeUp.start();
        makeUp2.start();
    }
}
class LipStick {}
class Mirror {}
class MakeUp extends Thread {
    private int choice;
    private static LipStick stick = new LipStick();
    private static Mirror mirror = new Mirror();
    private String gril;
    MakeUp(int choice,String gril) {
        this.choice = choice;
        this.gril = gril;
    }
    @Override
    public void run() {
        makeup();
    }
    private void makeup() {
        if (choice == 0) {
            synchronized (mirror) {
                System.out.println(this.gril +"照镜子");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (stick) {
                    System.out.println(this.gril + "涂口红");
                }
            }
        } else {
            synchronized (stick) {
                System.out.println(this.gril + "涂口红");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mirror) {
                    System.out.println(this.gril +"照镜子");
                }
            }
        }
    }
}