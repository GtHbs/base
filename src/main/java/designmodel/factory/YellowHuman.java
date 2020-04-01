package designmodel.factory;

/**
 * @author: 李昭
 * @Date: 2020/3/29 19:10
 */
public class YellowHuman implements Human {
    @Override
    public void getColor() {
        System.out.println("yellow");
    }

    @Override
    public void talk() {
        System.out.println("dragon`s descendant");
    }
}
