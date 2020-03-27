package jdbc.bean;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private int id;
    private String name;
    private Date orderDate;
    private double balance;
    public Order() {
    }

    public Order(int id, String name, Date orderDate,double balance) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orderDate=" + orderDate +
                ", balance=" + balance +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                Objects.equals(getName(), order.getName()) &&
                Objects.equals(getOrderDate(), order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOrderDate());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
