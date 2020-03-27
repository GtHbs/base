package lambda;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 21:36
 */
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
