package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable {

    private DatagramSocket server;

    public TalkReceive(int port) throws SocketException {
        server = new DatagramSocket(port);
    }

    @Override
    public void run() {
        System.out.println("接收数据...");
        while (true) {
            byte[] bytes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length);
            try {
                server.receive(packet);
                bytes = packet.getData();
                String s = new String(bytes, 0, packet.getLength());
                System.out.println(Thread.currentThread().getName()+"说:"+s);
                if (s.toUpperCase().equals("BYE"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.close();
    }
}
