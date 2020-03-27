package enums;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 20:19
 */
public enum PizzaDeliveryStrategy {
    /**
     * 枚举实现策略模式
     */
    EXPRESS {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("express");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pizza) {
            System.out.println("normal");
        }
    };

    public abstract void deliver(Pizza pizza);
}
class test {
    public static void main(String[] args) {
        Pizza pizza = new Pizza();
    }
}