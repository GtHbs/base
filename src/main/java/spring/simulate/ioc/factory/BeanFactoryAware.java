package spring.simulate.ioc.factory;

/**
 * @author 李昭
 */
public interface BeanFactoryAware {

    /**
     * 绑定beanFactory
     *
     * @param beanFactory
     * @throws Exception
     */
    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
