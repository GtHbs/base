package objectoriented.inherit;

public class test {
    public static void main(String[] args) {
        a a = new b();
    }
}
class a
{
    public void call()
    {
        System.out.println("call");
    }
}

class b extends a
{
    @Override
    public void call() {
        System.out.println("call2");
    }
    public void shout()
    {
        System.out.println("shout!!");
    }
}

