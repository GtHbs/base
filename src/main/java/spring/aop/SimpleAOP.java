package spring.aop;

import java.lang.reflect.Proxy;

/**
 * @author: 李昭
 * @Date: 2020/3/25 17:24
 */
@SuppressWarnings("all")
public class SimpleAOP {

    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}
