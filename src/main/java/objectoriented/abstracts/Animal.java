package objectoriented.abstracts;

/**
 * @author 李昭
 */
public abstract class Animal {
    abstract public void shout();       //拥有抽象方法的类必须是抽象类,继承的类必须实现抽象方法,且抽象类不能被实例化
    public void run()
    {
        System.out.println("run");
    }
}
class Dog extends Animal
{

    @Override
    public void shout() {
        System.out.println("Dog yell!!!");
    }

    @Override
    public void run() {
        super.run();
    }
}

class AnimalTest
{
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.run();
        dog.shout();
    }
}