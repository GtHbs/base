package collection.arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 19:27
 */
public class TestArrays {
    private static int[] arr = {6,2,4,1,5,7,98};

    public void traverse(int[] a) {
        for (int i : a) {
            System.out.print(i + "\t");
        }
    }

    @Test
    public void testSort() {
        traverse(arr);
        //对固定位置排序
        Arrays.sort(arr,1,5);
        System.out.println();
        traverse(arr);
    }

    @Test
    public void testCopyOf() {
        int[] ints = Arrays.copyOf(arr, 3);
        traverse(ints);
    }
}
