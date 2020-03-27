package collection.genertic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    @Test
    public void test01() {
        List<String> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Class<? extends List> class01 = list.getClass();
        Class<? extends List> class02 = list2.getClass();
        /**
         * 结果为true,因为泛型只是在编译阶段有效
         */
        System.out.println(class01.equals(class02));
    }
}
