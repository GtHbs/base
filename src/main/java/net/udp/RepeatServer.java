package net.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 */
public class RepeatServer {
    public static void main(String[] args) throws Exception {
        System.out.println("接收数据...");
        DatagramSocket server = new DatagramSocket(6666);
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length);
            server.receive(packet);
            bytes = packet.getData();
            String s = new String(bytes, 0, packet.getLength());
            System.out.println(s);
            if (s.toUpperCase().equals("BYE"))
                break;
        }
        server.close();
    }
}
class RepearClient {
    public static void main(String[] args) throws Exception {
        System.out.println("发送数据....");
        DatagramSocket client = new DatagramSocket(8888);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in,"UTF-8"));
        while (true) {
            String line = reader.readLine();
            byte[] data = line.getBytes();
            DatagramPacket packet = new DatagramPacket(data,0,data.length,
                    new InetSocketAddress("localhost",6666));
            client.send(packet);
            if (line.toUpperCase().equals("BYE"))
                break;
        }
        client.close();
    }
}