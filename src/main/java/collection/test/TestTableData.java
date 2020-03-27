package collection.test;

import java.util.*;

/**
 * @author 李昭
 */
public class TestTableData {
    public static void main(String[] args) {
        List<Map<Integer,Employee>> list = new ArrayList<>();
        Employee e1 = new Employee(1,"a",20,22, new Date());
        Employee e2 = new Employee(2,"b",21,23, new Date());
        Employee e3 = new Employee(3,"c",22,24, new Date());
        Employee e4 = new Employee(4,"d",23,25, new Date());
        Map<Integer,Employee> map = new HashMap<>();
        map.put(1,e1);
        map.put(2,e2);
        map.put(3,e3);
        map.put(4,e4);
        list.add(map);
    }
}
class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;
    private Date hire_date;

    public Employee() {
    }

    public Employee(int id, String name, int age, double salary, Date hire_date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.hire_date = hire_date;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() &&
                getAge() == employee.getAge() &&
                Double.compare(employee.getSalary(), getSalary()) == 0 &&
                Objects.equals(getName(), employee.getName()) &&
                Objects.equals(getHire_date(), employee.getHire_date());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getSalary(), getHire_date());
    }
}