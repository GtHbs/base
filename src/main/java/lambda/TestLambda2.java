package lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 语法格式一:
 * 无参数,无返回值 () -> System.out.println("hello World");
 * 语法格式二:
 * 一个参数(小括号可以不写),无返回值
 * 语法格式三:
 * 多个参数,有返回值,并且lambda体中有多条语句
 * 语法格式四:
 * 多个参数,只有一条语句,大括号和return可以省略
 * 语法格式五:
 * 参数列表中参数类型可以不写
 *
 * 函数式接口: 接口中只有一个抽象方法的接口,可以使用@FunctionalInterface标注用来检查是否是函数式接口
 *
 * @author: 李昭
 * @Date: 2020/3/23 21:57
 */
public class TestLambda2 {

    @Test
    public void test() {
        Runnable r = () -> System.out.println("hello World");
        r.run();
    }

    @Test
    public void test02() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("邓思萱");
    }

    @Test
    public void test03() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        Comparator<Integer> c = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test04() {
        Integer value = getValue(20, (a) -> a / 2);
        System.out.println(value);
    }

    public Integer getValue(Integer num,Caculate<Integer> caculate) {
        return caculate.getValue(num);
    }


}
