package objectoriented.constructor;

@SuppressWarnings("all")
public class PersonTest {
    public static void main(String[] args) {
        person p = new person();
        System.out.println(p.display());
        p.walk();
    }
}
class person{
    public person(){}
    String name;
    boolean isMarried;      //默认为false
    int age;
    public person(String name,boolean isMarried,int age){
        this.name = name;
        this.isMarried = isMarried;
        this.age = age;
    }
    public void walk()
    {
        System.out.println("人走路...");
    }
    public String display(){
        return "名字是:"+name+",婚姻状况:"+isMarried;
    }

    /**
     * 对于代码块,构造器初始化完成之后就是初始化代码块,
     * 直接给类的属性赋值不需要this,使用构造器给属性赋值
     * 需要this
     */
    {
        name = "刘国倩";
        isMarried = false;
        age = 22;
    }
    class pet{
        String name;
        float weight;
    }
}