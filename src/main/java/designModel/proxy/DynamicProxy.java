package designModel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Enzo Cotter on 2020/2/18.
 */
public class DynamicProxy {
    public static void main(String[] args) {
        ProxyHandler handler = new ProxyHandler();
        Subject bind = (Subject) handler.bind(new RealSubject());
        bind.doSomething();
    }
}

interface Subject {
    void doSomething();
}

/**
 * 真实角色
 */
class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("real do...");
    }
}

class ProxyHandler implements InvocationHandler {

    private Object obj;     //真实对象

    /**
     * 返回代理对象
     *
     * @param tar
     * @return
     */
    public Object bind(Object tar) {
        this.obj = tar;
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj, args);
    }
}