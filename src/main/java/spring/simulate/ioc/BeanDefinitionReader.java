package spring.simulate.ioc;

import java.io.FileNotFoundException;

/**
 * @author 李昭
 */
public interface BeanDefinitionReader {

    /**
     * 加载bean的定义
     *
     * @param location
     * @throws FileNotFoundException
     * @throws Exception
     */
    void loadBeanDefinitions(String location) throws FileNotFoundException, Exception;

}
