package designmodel.factory;

/**
 * @author: 李昭
 * @Date: 2020/3/29 19:09
 */
public class WhitePig implements Human {
    @Override
    public void getColor() {
        System.out.println("white");
    }

    @Override
    public void talk() {
        System.out.println("white pig");
    }
}
