package net.udp;

import org.junit.jupiter.api.Test;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 */
public class UdpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("接收数据...");
        DatagramSocket server = new DatagramSocket(6666);
        byte[] container = new byte[1024 * 1024];
        DatagramPacket packet = new DatagramPacket(container, 0, container.length);
        server.receive(packet);
        container = packet.getData();
        //将图片写出
        /*
        FileImageOutputStream outputStream = new FileImageOutputStream(
                new File("C:\\Users\\李昭\\Desktop\\s.gif"));
        outputStream.write(container,0,container.length);
        */
        /*
        ObjectInputStream inputStream = new ObjectInputStream(
                new BufferedInputStream(new ByteArrayInputStream(container)));
        Employee o = (Employee) inputStream.readObject();
        System.out.println(o);
        */
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(container));
        int i = stream.readInt();
        boolean b = stream.readBoolean();
        System.out.println(container.toString());
        System.out.println(i +":"+b);
        server.close();
    }

    /*
        junit测试单元不能进行控制台输入
     */
    @Test
    public void server() throws IOException {

    }

    @Test
    public void client() throws IOException {

    }


}
class test {
    @Test
    public static void tests() throws IOException {
        FileImageInputStream stream = new FileImageInputStream(
                new File("C:\\Users\\李昭\\Pictures\\wallpaper\\神社.gif"));
        FileImageOutputStream fileImageOutputStream = new FileImageOutputStream(
                new File("C:\\Users\\李昭\\Desktop\\s.gif"));
        int c = 0;
        while ((c = stream.read()) != -1) {
            //outputStream.write(bytes,0,c);
            fileImageOutputStream.write(c);
        }
    }

    public static void main(String[] args) throws IOException {
        tests();
    }
}