package enums;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 19:37
 */
public class TestEnum {
    public static void main(String[] args) {
        System.out.println(OrderStatus.PAY.name());
        System.out.println(OrderStatus.SUCCESS.getClass());
    }
}
