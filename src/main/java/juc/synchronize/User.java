package juc.synchronize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 李昭
 * @Date: 2020/3/27 21:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String name;
    private Double salary;
    private Boolean flag;
    private Integer age;
}
