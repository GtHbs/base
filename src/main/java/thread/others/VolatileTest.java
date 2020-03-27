package thread.others;

/**
 * volatile 用于数据的同步
 * 该关键字的作用为,其他线程修改共享数据后能立即修改并且被其他线程所看到
 * @author 李昭
 */
public class VolatileTest {
    private volatile static int num = 0;
    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            while (num == 0) {
                System.out.println("1");
            }
        }).start();
        Thread.sleep(1000);
        num = 1;
    }

}
