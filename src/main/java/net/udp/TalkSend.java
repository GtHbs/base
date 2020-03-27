package net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 */
public class TalkSend implements Runnable {

    private DatagramSocket client;
    private BufferedReader reader;
    private String ip;
    private int toPort;
    public TalkSend(int port,String ip,int toPort) throws SocketException, UnsupportedEncodingException {
        this.ip = ip;
        this.toPort = toPort;
        client = new DatagramSocket(port);
        reader = new BufferedReader(
                new InputStreamReader(System.in,"UTF-8"));
    }

    @Override
    public void run() {
        System.out.println("数据发送,输入bye结束...");
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                byte[] data = line.getBytes();
                DatagramPacket packet = new DatagramPacket(data,0,data.length,
                        //发送的ip和端口
                        new InetSocketAddress(ip,toPort));
                client.send(packet);
                if (line.toUpperCase().equals("BYE"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client.close();
    }
}
