package spring.aop.test;

import org.junit.jupiter.api.Test;
import spring.aop.Advice;
import spring.aop.BeforeAdvice;
import spring.aop.MethodInvocation;
import spring.aop.SimpleAOP;

/**
 * @author: 李昭
 * @Date: 2020/3/25 17:26
 */
public class SimpleAOPTest {

    @Test
    public void getProxy() throws Exception {
        // 1. 创建一个 MethodInvocation 实现类
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 2. 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 3. 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl, beforeAdvice);

        helloServiceImplProxy.sayHelloWorld();
    }
}
