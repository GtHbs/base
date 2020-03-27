package objectoriented.inherit;
@SuppressWarnings("all")
public class Person {
    private String name;
    private char sex;
    private int age;

    public Person(String name, char sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
