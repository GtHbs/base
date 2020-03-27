package reflection.test;

import org.junit.jupiter.api.Test;
import reflection.bean.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PersonTest {
    @Test
    public void test01() {
        try {
            Class person = Class.forName("reflection.bean.Person");
            Class personClass = Person.class;
            Person p = new Person();
            Person p2 = new Person("alone",2);
            System.out.println(p.getClass());
            System.out.println(person);
            System.out.println(personClass);
            /**
             * 同一个字节码文件只会被加载一次,属性是后面赋值
             */
            System.out.println(person == p2.getClass());
            System.out.println(person == personClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws Exception {
        Class person = Person.class;
        //获取所有public修饰的成员变量
        Field[] fields = person.getFields();
        Constructor[] constructors = person.getConstructors();
        Method[] methods = person.getMethods();
        for (Method method : methods) {
            //System.out.println(method.getName());
        }
        for (Constructor constructor : constructors) {
            //System.out.println(constructor.getName());
        }
        Field email = person.getField("email");
        //获取所有的成员变量
        Field[] declaredFields = person.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            System.out.println(field.getName());
        }
        Person p = new Person();
        Object value = email.get(p);
        System.out.println(value);
        email.set(p,"dsx@alone.com");
        System.out.println(email.get(p));
        Method eat = person.getDeclaredMethod("eat",String.class);
        eat.setAccessible(true);
        eat.invoke(p,"dsx");      //执行方法
    }


    @Test
    public void test03() throws Exception {
        Class<?> aClass = Class.forName("reflection.bean.Person");
        Person o = (Person) aClass.getDeclaredConstructor().newInstance();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.print(field.getName()+"\t");
        }
        System.out.println();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            System.out.println(method.getName());
            method.invoke(o);
        }
    }

}
