package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        IPhone iPhone = new IPhone();
        Class<? extends IPhone> aClass = iPhone.getClass();
        Constructor<?>[] constructors = aClass.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; ++i) {
            System.out.print(Modifier.toString(constructors[i].getModifiers())+" : ");
            Class<?>[] types = constructors[i].getParameterTypes();
            for (int j = 0; j < types.length; ++j) {
                System.out.print(types[j].getName() +" : ");
            }
            System.out.println();
        }
        System.out.println();
        Class[] p = {Integer.class,String.class};
        //获取特定的构造器
        Constructor<? extends IPhone> constructor = aClass.getDeclaredConstructor(p);
        IPhone alone = constructor.newInstance(22, "alone");
        System.out.println(alone);
        Class[] s = {String.class};
        Constructor<? extends IPhone> declaredConstructor = aClass.getDeclaredConstructor(s);
        declaredConstructor.setAccessible(true);
        IPhone dsx = declaredConstructor.newInstance("dsx");
        System.out.println(dsx);

        Method hhh = aClass.getDeclaredMethod("test", s);
        hhh.setAccessible(true);
        Object[] objs = {"ssss"};
        hhh.invoke(iPhone,objs);
    }
}
class IPhone {
    private Integer age;
    private String name;
    private Integer testInt;

    public IPhone(Integer age) {
        this.age = age;
    }

    public IPhone(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    private IPhone(String name) {
        this.name = name;
    }

    public IPhone() {
    }

    private void test(String s) {
        System.out.println("private test " + s);
    }

    @Override
    public String toString() {
        return "IPhone{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", testInt=" + testInt +
                '}';
    }
}