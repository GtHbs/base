package objectoriented.memorymodel;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: 李昭
 * @Date: 2020/3/27 21:28
 */
@SuppressWarnings("all")
public class ObjectLayoutTest {
    public static void main(String[] args) {
        Object object = new Object();
        /*
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION
         *       0     4        (object header)
         *       4     4        (object header)    上下共8个字节为头信息的markword
         *       8     4        (object header)     classPointer
         *      12     4        (loss due to the next object alignment) 头信息必须为8的倍数所以需要补4个字节
         * Instance size: 16 bytes
         */
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        System.out.println("-----------------------------------------------------");
        synchronized (object) {
            /*
                java.lang.Object object internals:
                OFFSET  SIZE   TYPE DESCRIPTION         VALUE
                   0     4        (object header)      f8 f3 15 03 (锁存放在markword中)
                   4     4        (object header)      00 00 00 00
                   8     4        (object header)      e5 01 00 20
                   12     4        (loss due to the next object alignment)
                Instance size: 16 bytes
             */
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
        //User user = new User("name",20.2,false,20);
        /*
        objectoriented.memorymodel.User object internals:
        OFFSET  SIZE                TYPE DESCRIPTION
            0     4                     (object header)
            4     4                     (object header)     上下共8个字节为头信息的markword
            8     4                     (object header)     classPointer(采用字节压缩,否则为8个字节)
            12     4                java.lang.String User.name     (object)
            16     4                java.lang.Double User.salary     20.2   字节压缩为4个字节
            20     4                java.lang.Boolean User.flag     false
            24     4                java.lang.Integer User.age      20
            28     4                (loss due to the next object alignment)
            Instance size: 32 bytes
         */
        //System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }
}
