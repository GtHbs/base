package generic;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 5:29 PM
 */
@SuppressWarnings("all")
public class GetMinNumber {
    /**
     * 使用泛型获取泛型数组中最小值
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (values[i].compareTo(min) > 0) {
                min = values[i];
            }
        }
        return min;
    }
}
