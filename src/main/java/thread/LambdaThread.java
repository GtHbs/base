package thread;

/**
 * @author 李昭
 */
public class LambdaThread {
    /**
     * 静态内部类
     */
    static class test implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ++i) {
                System.out.println("玩游戏");
            }
        }
    }

    public static void main(String[] args) {
        //new Thread(new test()).start();
        //局部内部类
        class test2 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    System.out.println("玩游戏");
                }
            }
        }
        new Thread(new test2()).start();
        new Thread(()->{
            System.out.println("aaa");
        }).start();
    }
}
interface like {
    void lambda();
}
class likes implements like {

    @Override
    public void lambda() {
        System.out.println("like");
    }

    public static void main(String[] args) {
        like like = () -> {
            System.out.println("aa");
        };
    }
}