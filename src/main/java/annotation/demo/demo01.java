package annotation.demo;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author 李昭
 * @version 1.0
 * @since 1.5
 */
@SuppressWarnings("all")
public class demo01 {

    /**
     *
     * @param a     整数
     * @param b     整数
     * @return      返回和
     */
    public int add(int a,int b) {
        return a + b;
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("2");
            }
        });
        t.start();

        t.join();
        System.out.print("1");
    }
}

@SuppressWarnings("all")
class User implements Comparable<User> {

    private Integer age;


    public User(final Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }
}
@SuppressWarnings("all")
abstract class Authority {
    abstract void s();
}