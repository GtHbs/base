package thread.others;

public class InheritThreadLocal {
    private static ThreadLocal<Integer> local = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        local.set(2);
        System.out.println(Thread.currentThread().getName()+":"+local.get());
        local.set(200);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+":"+local.get());
        }).start();
    }
}
