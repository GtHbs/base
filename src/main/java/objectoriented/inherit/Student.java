package objectoriented.inherit;

/**
 * 学生类
 */
@SuppressWarnings("all")
public class Student extends Person {
    private long number;
    private int math;
    private int english;
    private int computer;

    public Student(String name, char sex, int age, long number, int math, int english, int computer) {
        super(name, sex, age);
        this.number = number;
        this.math = math;
        this.english = english;
        this.computer = computer;
    }
    public double aver()
    {
        double avg = (number + math + english + computer) / 4;
        return avg;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
