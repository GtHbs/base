package enums;

/**
 * 懒汉式创建单例模式
 *
 * @Author: 李昭
 * @Date: 2020/3/23 20:17
 */
public enum PizzaDelivery {
    /**
     * 枚举实例变量
     */
    INSTANCE;

    PizzaDelivery() {
    }
    public static PizzaDelivery getInstance() {
        return INSTANCE;
    }
}
