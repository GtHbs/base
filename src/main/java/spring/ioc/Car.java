package spring.ioc;

import lombok.Data;

/**
 * @author: 李昭
 * @Date: 2020/3/25 16:58
 */
@Data
public class Car {
    private String name;
    private String length;
    private String width;
    private String height;
    private Wheel wheel;
}
