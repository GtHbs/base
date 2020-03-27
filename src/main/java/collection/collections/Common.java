package collection.collections;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 18:45
 */
public class Common {


    private static List<Integer> list = new ArrayList<>();

    static {
        Random random = new Random();
        for (int i = 0; i < 15; ++i) {
            list.add(random.nextInt(100));
        }
    }

    @Test
    public void testReverse() {
        //[11, 2, 21, 1]
        System.out.println(list);
        Collections.reverse(list);
        //[1, 21, 2, 11]
        System.out.println(list);
    }

    @Test
    public void testShuffle() {
        System.out.println(list);
        Collections.shuffle(list);
        //1, 2, 21, 11]
        System.out.println(list);
    }

    @Test
    public void testSort() {
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list);
    }

    @Test
    public void testSwap() {
        System.out.println(list);
        Collections.swap(list, 1, 2);
        //[11, 21, 2, 1]
        System.out.println(list);
    }

    @Test
    public void testRotate() {
        //[11, 2, 21, 1]
        System.out.println(list);
        Collections.rotate(list, 2);
        //[21, 1, 11, 2]
        System.out.println(list);
    }

    @Test
    public void binarySearch() {
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        int i = Collections.binarySearch(list, 16);
        System.out.println(i);
    }

    @Test
    public void testMax() {
        System.out.println(list);
        Integer max = Collections.max(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(max);
    }

    @Test
    public void testFill() {
        System.out.println(list);
        Collections.fill(list, 1);
        System.out.println(list);
    }
}
