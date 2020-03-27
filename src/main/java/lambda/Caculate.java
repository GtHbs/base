package lambda;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 22:24
 */
@FunctionalInterface
public interface Caculate<T> {
    T getValue(T a);
}
