package juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 3:31 PM
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        AtomicIntegerArray array = new AtomicIntegerArray(nums);
        for (int i = 0; i < nums.length; ++i) {
            System.out.println(array.get(i));
        }
        System.out.println(array.getAndSet(0, 2) + "---" + array);
        System.out.println(array.getAndIncrement(1) + "---" + array);
        System.out.println(array.getAndAdd(1, 4) + "---" + array);

    }
}
