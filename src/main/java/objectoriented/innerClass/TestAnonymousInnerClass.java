package objectoriented.innerClass;

/**
 * @author 李昭
 */
public class TestAnonymousInnerClass {
    public static void test(ins i)
    {
        i.show();
    }
    public static void main(String[] args) {
        TestAnonymousInnerClass.test(new ins() {
            @Override
            public void show() {
                System.out.println("Anonymous Inner Class");
            }
        });
        TestAnonymousInnerClass.show();
    }
    //方法中的内部类
    public static void show()
    {
        class inner
        {
            public void fun()
            {
                System.out.println("fun");
            }
        }
        new inner().fun();
    }


}
interface ins
{
    void show();
}