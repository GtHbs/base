package spring.simulate.ioc;

/**
 * bean的后置处理器
 *
 * @author 李昭
 */
public interface BeanPostProcessor {

    /**
     * 实例化之前调用(与AOP切入有关)
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    /**
     * 实例化之后调用
     *
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
