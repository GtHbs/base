package lambda;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 22:37
 */
@FunctionalInterface
public interface Functions<T,R> {
    R getValue(T t,R r);
}
