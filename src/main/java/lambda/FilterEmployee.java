package lambda;

/**
 * @Author: 李昭
 * @Date: 2020/3/23 21:37
 */
public class FilterEmployee implements Predicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
