package thread.others;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 李昭
 */
public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //执行一次
        //timer.schedule(new TimerTasks(),5000);
        //2秒后执行一次,每隔2秒执行一次
        timer.schedule(new TimerTasks(),2000,2000);
    }
}
class TimerTasks extends TimerTask {

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Hello World!!!");
        }
        System.out.println("end---");
    }
}