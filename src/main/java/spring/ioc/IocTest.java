package spring.ioc;

import org.junit.jupiter.api.Test;

/**
 * @author: 李昭
 * @Date: 2020/3/25 17:02
 */
public class IocTest {

    @Test
    public void test() throws Exception {
        String path = "D:\\program\\Java\\JAVASE\\basic\\src\\spring\\ioc.xml";
        SimpleIOC ioc = new SimpleIOC(path);
        Wheel wheel = (Wheel) ioc.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) ioc.getBean("car");
        System.out.println(car);
    }
}
