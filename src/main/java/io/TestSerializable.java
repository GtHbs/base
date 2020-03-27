package io;

import java.io.*;

/**
 * @author 李昭
 */
public class TestSerializable {
    public static void main(String[] args) {
        T t = new T();
        t.k = 2;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\李昭\\Desktop\\test.log");
            ObjectOutputStream stream = new ObjectOutputStream(fos);
            stream.writeObject(t);
            stream.flush();
            stream.close();
            FileInputStream inputStream = new FileInputStream("C:\\Users\\李昭\\Desktop\\test.log");
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            T ts = (T)objectInputStream.readObject();
            System.out.println(ts.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
class T implements Serializable
{
    int i = 0,j = 9;
    double d = 2.4;
    transient int k = 0;        //序列化时该属性不写出
    @Override
    public String toString() {
        return "T{" +
                "i=" + i +
                ", j=" + j +
                ", d=" + d +
                ", k=" + k +
                '}';
    }
}