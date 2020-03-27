package collection.comparable;

/**
 * @author 李昭
 */
public class Employee implements Comparable<Employee> {

    private int age;
    private double salary;
    private String name;

    public Employee(int age, double salary, String name) {
        this.age = age;
        this.salary = salary;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Employee o) {
        return this.salary >= o.salary ? (this.salary == o.salary ? 0 : 1) : -1;
    }

    public static void main(String[] args) {
        Employee employee = new Employee(10,2.5,"a");
        Employee employee2 = new Employee(10,2.4,"a");
        int i = employee.compareTo(employee2);
        System.out.println(i);
    }
}
