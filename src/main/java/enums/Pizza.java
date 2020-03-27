package enums;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 19:38
 */
public class Pizza {
    public OrderStatus status;

    public enum pizzaStatus {
        ORDERED,
        READY,
        DELIVERED
    }

    public boolean isDelivered() {
        if (getStatus() == OrderStatus.SUCCESS) {
            return true;
        }
        return false;
    }


    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }
}
