package reflection.test;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * @Author: 李昭
 * @Date: 2020/3/9 12:46
 */
public class StringTest {

    @Test
    public void test01() throws Exception {
        String s = "dsx";
        Method method = s.getClass().getMethod("toUpperCase");
        System.out.println(method.invoke(s,null));
        int[] arr = {2,1,566,2378,2,4};
        int[] sort = bubbleSort(arr);
        for (int i : sort) {
            System.out.println(i);
        }
    }
    public int[] bubbleSort(int[] arr) {
        int temp,i,j;
        int length = arr.length - 1;
        boolean flag = false;
        for (i = length; i >= 1; --i) {
            flag = false;
            for (j = 1; j <= i; ++j) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return arr;
            }
        }
        return arr;
    }
}
