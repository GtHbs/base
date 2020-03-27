package lambda;

import lombok.Data;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 21:26
 */
public class Employee {
    private String name;
    private Integer age;
    private Double salary;

    public Employee() {
    }

    public Employee(final String name, final Integer age, final Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
