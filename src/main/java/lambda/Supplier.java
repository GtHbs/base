package lambda;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 22:40
 */
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
