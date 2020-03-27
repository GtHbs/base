package spring.aop;

/**
 * 执行器
 *
 * @author: 李昭
 * @Date: 2020/3/25 17:21
 */
public interface MethodInvocation {
    /**
     * 切面方法执行器
     */
    void invoke();
}
