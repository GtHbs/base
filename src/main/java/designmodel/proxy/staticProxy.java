package designmodel.proxy;

/**
 * 静态代理
 *  1,真实角色
 *  2,代理角色
 * @author 李昭
 */
public class staticProxy {
    public static void main(String[] args) {
//        Marry self = new Self();
//        WeddingCompany company = new WeddingCompany(self);
//        company.happyMarry();
        Class i = Integer.class;
        Class n = i.getSuperclass();
        System.out.println(n);
        Class o = n.getSuperclass();
        System.out.println(o);
        System.out.println(o.getSuperclass());
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class i1 : is) {
            System.out.println(i1);
        }
    }
}
interface Marry {
    void happyMarry();
}
//真实对象
class Self implements Marry {

    @Override
    public void happyMarry() {
        System.out.println("Happy Married!!!");
    }
}
//代理对象
class WeddingCompany implements Marry {

    //真实角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        ready();
        this.target.happyMarry();
        after();
    }
    private void ready() {
        System.out.println("准备结婚!!");
    }
    private void after() {
        System.out.println("结婚完毕!!");
    }
}