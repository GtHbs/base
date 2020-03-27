package objectoriented.overLoad;

@SuppressWarnings("all")
public class overLoad {
    private static String name = "alone";
    private String gender = "male";
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        char[] b = new char[]{'a','b','c'};
        System.out.println(a);
        System.out.println(b);
        String s = "world";
        s = test(s);
        System.out.println(s);
    }

    /**
     * 重载:
     *  1,参数列表不同
     *  2,返回值类型相同
     *  3,访问权限修饰符相同
     * @param a
     */
    public static void sayHello(int a)
    {
        System.out.println(a);
    }
    public static void sayHello(String name,int age){
        System.out.println(name+","+age);
    }

    public static String test(String s)
    {
        s = "hello";
        return s;
    }
}
