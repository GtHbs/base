package designmodel.factory;

/**
 * @author: 李昭
 * @Date: 2020/3/29 19:14
 */
public class Test {
    public static void main(String[] args) {
        AbstractHumanFactory factory = new HumanFactory();
        Human human = factory.createHuman(Nigger.class);
        human.getColor();
        human.talk();
    }
}
