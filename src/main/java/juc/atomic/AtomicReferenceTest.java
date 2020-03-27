package juc.atomic;

import juc.bean.Person;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: 李昭
 * @Date: 3/24/2020 3:36 PM
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Person> reference = new AtomicReference<>();
        Person person = new Person(10, "alone");
        reference.set(person);
        Person person2 = new Person(20, "jim");
        reference.compareAndSet(person, person2);
        System.out.println(reference.get().getAge());
        System.out.println(reference.get().getName());
    }
}
