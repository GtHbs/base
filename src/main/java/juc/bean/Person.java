package juc.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 3:35 PM
 */
@Data
public class Person implements Serializable {

    private static final long serialVersionUID = -7899664223507183502L;
    private Integer age;
    private String name;

    public Person() {
    }

    public Person(final Integer age, final String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
