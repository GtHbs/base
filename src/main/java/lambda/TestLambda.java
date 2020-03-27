package lambda;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 21:23
 */
public class TestLambda {

    @Test
    public void test01() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        TreeSet<Integer> set = new TreeSet<>(comparator);
    }

    public void test02() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> set = new TreeSet<>(comparator);
    }

    private List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("A", 20, 20D),
            new Employee("B", 30, 30D),
            new Employee("C", 40, 40D),
            new Employee("D", 50, 50D),
            new Employee("E", 60, 60D)
    ));

    @Test
    public void filterEmployees() {
        List<Employee> list = new ArrayList<>();
        /**
         * 使用策略设计模式
         */
        FilterEmployee employee = new FilterEmployee();
        for (Employee e : employees) {
            if (employee.test(e)) {
                list.add(e);
            }
        }
        System.out.println(list);
    }

    @Test
    public void test() {
        Predicate<Employee> predicate = new Predicate<Employee>() {

            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 20;
            }
        };
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                list.add(employee);
            }
        }
        System.out.println(list);
    }


    public List<Employee> filter(List<Employee> list,Predicate<Employee> predicate) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee e : employees) {
            if (predicate.test(e)) {
                employeeList.add(e);
            }
        }
        return employeeList;
    }

    @Test
    public void test03() {
        List<Employee> list = filter(employees, (e) -> e.getSalary() <= 5000);
        list.forEach(System.out::println);
    }

    @Test
    public void test04() {
        employees.stream().filter((e)->e.getSalary() >= 20).forEach(System.out::println);
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }

    @Test
    public void test05() {
        Collections.sort(employees,(e1,e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        });
        employees.forEach(System.out::println);
    }

    @Test
    public void test06() {
        String undercase = undercase("hello world", (s) -> s.toUpperCase());
        System.out.println(undercase);
    }
    public String undercase(String s,UndercaseString string) {
        return string.undercase(s);
    }

    @Test
    public void test07() {
        long value = getValue(100L, 200L, (l1, l2) -> l1 + l2);
        System.out.println(value);
    }

    public long getValue(long l1,long l2,Functions<Long,Long> functions) {
        return functions.getValue(l1,l2);
    }
}
