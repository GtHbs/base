package interfacemethod;

/**
 * @author: 李昭
 * @Date: 3/24/2020 7:50 PM
 */
public interface Vehicle {
    /**
     * Java8新特性
     */
    default void print() {
        System.out.println("I`am a car");
    }

    /**
     * 静态默认方法
     */
    static void StaticPrint() {
        System.out.println("static method");
    }
}
class Bycicle implements Vehicle {
    public static void main(String[] args) {
        Vehicle vehicle = new Bycicle();
        vehicle.print();
        Vehicle.StaticPrint();
    }
}
