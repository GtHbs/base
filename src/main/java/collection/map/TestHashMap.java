package collection.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author 李昭
 */
public class TestHashMap {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        /**
         * 此方法返回小于该数字的最大的一个2的幂
         */
        System.out.println(Integer.highestOneBit(4));
        System.out.println(Integer.highestOneBit(10));
        System.out.println(Integer.highestOneBit(33));
        Map<Integer,String> map = new HashMap<>();
        String a = map.put(1, "a");
        System.out.println(a);
        //map.put(1,"a");
        //String b = map.put(1, "b"); //当发生覆盖时,会将覆盖的值返回
        //System.out.println(b);
    }
}
class Employee
{
    private int id;
    private String name;
    private double salary;

    public Employee() {}

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}