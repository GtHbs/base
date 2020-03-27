package objectoriented.inherit;

/**
 * @author 李昭
 */
public class Cylinder extends Circle {
    private double length;

    public Cylinder() {
        this.length = 1;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    public double findVolume()
    {
        return Math.abs(findArea() * length);
    }
}
class CylinderTest
{
    public static void main(String[] args) {
        Cylinder cylinder = new Cylinder();
        cylinder.setLength(20);
        cylinder.setRadius(2);
        System.out.println(cylinder.findVolume());
    }

}

/**
 * 重载,重写和隐藏
 * 重载:方法名,返回类型相同,参数列表不同
 * 重写:子类重写父类方法方法名返回类型和参数列表都相同,但仅限于非静态方法
 * 隐藏:以父类构造子类对象,调用两个类中共有的静态方法时调用的是父类静态方法
 */
class Test {
    public static void main(String[] args)  {
        Shape shape = new Circles();
        System.out.println(shape.name);
        shape.printType();      //重写
        shape.printName();    //覆盖
        System.out.println(Shape.age);
    }
}
class Shape {
    public String name = "shape";
    public static int age = 10;
    public Shape(){
        System.out.println("shape constructor");
    }
    public void printType() {
        System.out.println("this is shape");
    }
    public static void printName() {
        System.out.println("shape");
    }
}
class Circles extends Shape {
    public String name = "circle";
    public Circles() {
        System.out.println("circle constructor");
    }
    @Override
    public void printType() {
        System.out.println("this is circle");
    }
    public static void printName() {
        System.out.println("circle");
    }
}