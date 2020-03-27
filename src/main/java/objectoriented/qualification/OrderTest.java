package objectoriented.qualification;
@SuppressWarnings("all")
public class OrderTest {
    public static void main(String[] args) {
        Order order = new Order();
        order.eat();
        order.sleep();
        //order.say();
    }
}

/**
 * 各种修饰符权限
 *              类内部     同一个包     不同包的子类      同一个工程
 *  private       *
 *  default       *           *
 *  protected     *           *             *
 *  public        *           *             *               *
 */
class Order
{
    public String name;
    private int age;
    int orders;
    public void eat()
    {
        name = "alone";
        age = 10;
        orders = 100;
    }
    void sleep()
    {
        name = "alone";
        age = 10;
        orders = 100;
    }
    private void say()
    {
        name = "alone";
        age = 10;
        orders = 100;
    }
}