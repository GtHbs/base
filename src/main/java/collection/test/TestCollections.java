package collection.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 李昭
 */
public class TestCollections {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            list.add("b"+i);
        }
        list.add("a");
        System.out.println(list);
        Collections.shuffle(list);          //用于将数组打乱
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        /**
         * 排序的方式为:数字升序,字母按照字典排序
         */
        Collections.sort(list);
        System.out.println(list);
    }
}
