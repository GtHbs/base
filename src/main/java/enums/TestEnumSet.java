package enums;

import java.util.EnumSet;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 19:48
 */
public class TestEnumSet {
    private static EnumSet<OrderStatus> enumSet = EnumSet.of(OrderStatus.PAY, OrderStatus.SUCCESS, OrderStatus.UN_PAY);
    private OrderStatus status;

    public enum PizzaStatus {

    }

}
