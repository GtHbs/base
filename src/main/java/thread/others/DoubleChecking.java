package thread.others;

/**
 * 单例模式(懒汉式:需要的时候才创建对象):对外存在一个对象(多线程情况下)
 * 1,构造器私有化
 * 2,提供私有的静态属性
 * 3,提供公共的静态方法
 * @author 李昭
 */
public class DoubleChecking {
    /**
     * 原子操作:创建了实例对象之后立即将其返回到主存,以便其他访问的对象
     *         立即访问到该对象
     */
    private volatile static DoubleChecking instance;
    private DoubleChecking() {}
    /**
     * 存在问题:
     *      在同步块内,如果已经创建对象但是还没有返回回去,外部线程可能会返回一个
     *      没有初始化的对象
     * @return
     */
    public static DoubleChecking getInstance() {
        //避免不必要的同步
        if (instance != null) {
            return instance;
        }
        synchronized (DoubleChecking.class) {
            if (instance == null) {
                //可能会发生指令重排
                instance = new DoubleChecking();
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(DoubleChecking.getInstance());
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            System.out.println(DoubleChecking.getInstance());
        });
        thread2.start();
        System.out.println(DoubleChecking.getInstance());
    }
}