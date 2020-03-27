package objectoriented.interfcases;

/**
 * @author 李昭
 */
public class TestInterface {
    public static void main(String[] args) {

    }
}

/**
 * @author 李昭
 * 飞行接口
 */
interface Volant
{
    public static final int FLY_HEIGHT = 20000;
    public abstract void fly();
}


/**
 * @author 李昭
 * 善良接口
 */
interface Honest
{
    void helpOther();
}

class Angel implements Volant,Honest
{

    public void call()
    {
        System.out.println("call");
    }

    @Override
    public void fly() {
        System.out.println("Angel can fly "+FLY_HEIGHT +"meters high!!!");
    }

    @Override
    public void helpOther() {
        System.out.println("Help other!!!");
    }
}


class test
{
    public static void main(String[] args) {

    }
}



