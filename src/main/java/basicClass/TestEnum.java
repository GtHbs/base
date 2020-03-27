package basicClass;

/**
 * @author 李昭
 */
public class TestEnum {
    public static void main(String[] args) {
        System.out.println(Season.SPRING);
        Season s = Season.AUTUMN;
        switch (s)
        {
            case AUTUMN:
                System.out.println("autumn!!!");
                break;
            case SPRING:
                System.out.println("Spring!!!");
                break;
            case SUMMER:
                System.out.println("Summer!!!");
                break;
            case WINTER:
                System.out.println("Winter!!!");
                break;
        }
    }
}
enum Season
{
    SPRING,SUMMER,AUTUMN,WINTER
}