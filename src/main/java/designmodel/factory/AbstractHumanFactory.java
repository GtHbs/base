package designmodel.factory;

/**
 * @author: 李昭
 * @Date: 2020/3/29 19:11
 */
public abstract class AbstractHumanFactory {
    /**
     * 创造人类
     *
     * @param c
     * @return
     */
    public abstract Human createHuman(Class<? extends Human> c);
}
