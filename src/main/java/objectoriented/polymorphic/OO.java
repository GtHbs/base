package objectoriented.polymorphic;

/**
 * 封装,继承,多态
 */
@SuppressWarnings("all")
public class OO {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        /**
         * this.show(o)
         * super.show(o)
         * this.show((super)o)
         * super.show((super)o)
         */
        System.out.println("1--" + a1.show(b)); //a-a this.show((super)o)
        System.out.println("2--" + a1.show(c)); //a-a this.show((super)o)
        System.out.println("3--" + a1.show(d)); //a-d this.show(o)
        System.out.println("4--" + a2.show(b)); //b-a this.show((super)o)
        System.out.println("5--" + a2.show(c)); //b-a this.show((super)o)
        System.out.println("6--" + a2.show(d)); //a-d super.show(o)
        System.out.println("7--" + b.show(b));  //b-b this.show(o)
        System.out.println("8--" + b.show(c));  //b-b this.show((super)o)
        System.out.println("9--" + b.show(d));  //a-d super.show((super)o)
    }
}
class A {
    public String show(D obj) {
        return ("A and D");
    }
    public String show(A obj) {
        return ("A and A");
    }
}
class B extends A{
    public String show(B obj){
        return ("B and B");
    }
    public String show(A obj){
        return ("B and A");
    }
}
class C extends B{}
class D extends B{}
