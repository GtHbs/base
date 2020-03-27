package collection.genertic;

/**
 * @author 李昭
 */
public class TestGenertic {
    public static void main(String[] args) {
        Collection01 c = new Collection01();
        c.set("a",0);
        c.set(1,1);
        String str = (String) c.get(0);
        Integer ins = (Integer) c.get(1);
        System.out.println(str+"\t"+ins);
    }
}
class Collection01<E>
{
    Object[] objs = new Object[5];
    public void set(Object obj,int index)
    {
        objs[index] = obj;
    }
    public Object get(int index)
    {
        return objs[index];
    }
}