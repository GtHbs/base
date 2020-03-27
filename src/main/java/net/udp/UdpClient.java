package net.udp;


import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        System.out.println("发送方启动中...");
        //1,使用DatagramSocket指定端口,创建发送端
        DatagramSocket client = new DatagramSocket(8888);
        //2,准备数据转为字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        /*
            传输图片只能传输1k以内的图片
        FileImageInputStream stream = new FileImageInputStream(
                new File("C:\\Users\\李昭\\Pictures\\wallpaper\\神社.gif"));
        byte[] container = new byte[1024 * 1024];
        int r = 0;
        while ((r = stream.read()) != -1) {
            outputStream.write(container,0,r);
        }
        outputStream.flush();
         */

        /*
        ObjectOutputStream stream = new ObjectOutputStream(outputStream);
        Employee employee = new Employee(1,"a");        //服务端读取age为0
        stream.writeObject(employee);
         */
        DataOutputStream stream = new DataOutputStream(outputStream);
        stream.writeInt(1);
        stream.writeBoolean(true);
        stream.writeBytes("邓思萱");
        stream.flush();
        byte[] bytes = outputStream.toByteArray();;
        //3,封装为DatagramPacket包裹,需要指定目的地
        DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length,
                new InetSocketAddress("localhost",6666));        //4,发送包裹
        client.send(packet);
        //5,释放资源
        client.close();
    }
}
class Employee implements Serializable {
    private transient int age = 20;
    private String name;

    public Employee(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}