package spring.simulate.ioc.factory;

/**
 * @author 李昭
 */
public interface BeanFactory {
    /**
     * 核心接口
     * BeanFactory 加载 Bean 配置文件，将读到的 Bean 配置封装成 BeanDefinition 对象
     * 将封装好的 BeanDefinition 对象注册到 BeanDefinition 容器中
     * 注册 BeanPostProcessor 相关实现类到 BeanPostProcessor 容器中
     * BeanFactory 进入就绪状态
     * 外部调用 BeanFactory 的 getBean(String name) 方法，BeanFactory 着手实例化相应的 bean
     * 重复步骤 3 和 4，直至程序退出，BeanFactory 被销毁
     *
     * @param beanId
     * @return
     * @throws Exception
     */
    Object getBean(String beanId) throws Exception;
}
