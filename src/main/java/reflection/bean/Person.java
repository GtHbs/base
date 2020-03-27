package reflection.bean;

public class Person {
    private String name;
    private int age;
    public String email = "alone@gmail.com";
    public Person() {}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    private void eat(String name) {
        System.out.println("eat"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void publicMethod(String s) {
        System.out.println("public method:"+s);
    }

    private void privateMethod(String s) {
        System.out.println("private method:"+s);
    }
}
