package objectoriented.interfcases;

/**
 * @author 李昭
 */
public interface Animal {
    int EYES = 2;
    public void shout();
}
class cat implements Animal
{

    @Override
    public void shout() {
        System.out.println(EYES);
        System.out.println("cat yell!!!");
    }
}