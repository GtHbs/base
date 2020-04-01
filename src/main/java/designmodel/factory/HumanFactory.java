package designmodel.factory;

/**
 * @author: 李昭
 * @Date: 2020/3/29 19:12
 */
public class HumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman(Class<? extends Human> c) {
        Human human = null;
        try {
            human = (Human) Class.forName(c.getName()).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return human;
    }
}
