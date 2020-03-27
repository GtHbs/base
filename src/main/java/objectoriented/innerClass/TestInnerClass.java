package objectoriented.innerClass;

/**
 * @author 李昭
 */
public class TestInnerClass {
    public static void main(String[] args) {
        Outer.inner inner = new Outer().new inner();        //创建非静态内部类
        inner.show();
        Outer.inners inner1 = new Outer.inners();           //创建静态内部类
        inner1.show();
    }
}
class Outer
{
    private int age = 10;
    private static String name = "alone";
    public void outers()
    {

    }
    class inner
    {
        public void show()
        {
            System.out.println(Outer.this.age);
        }
    }
    static class inners
    {
        public void show()
        {
            System.out.println(Outer.name);
        }
    }
}