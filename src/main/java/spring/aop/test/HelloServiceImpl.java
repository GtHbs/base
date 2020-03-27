package spring.aop.test;

/**
 * @author: 李昭
 * @Date: 2020/3/25 17:26
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHelloWorld() {
        System.out.println("hello world!");
    }
}
