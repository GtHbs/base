package collection.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 19:21
 */
public class TestCollection {
    public static void main(String[] args) {
        /**
         * 不要使用Collections包装集合,效率非常低下
         */
        List<Integer> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        Collections.synchronizedList(list);
    }

    @Test
    public void testUtil() {
        List<Object> emptyList = Collections.emptyList();
        System.out.println(emptyList);
        List<Integer> singleList = new ArrayList<>();
        singleList.add(1);
        List<List<Integer>> list = Collections.singletonList(singleList);
        System.out.println(list);
        singleList.add(2);
        System.out.println(singleList);
    }
}
