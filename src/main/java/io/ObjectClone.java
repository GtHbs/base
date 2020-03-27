package io;

import juc.bean.Person;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 6:39 PM
 */
public class ObjectClone {
    public static void main(String[] args) throws Exception {
        Person person = new Person(10, "alone");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(person);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Person o = (Person) objectInputStream.readObject();
        System.out.println(o);
        String s = null;
        if (Objects.equals(s, "hello")) {

        }
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.add(2);
    }
}
