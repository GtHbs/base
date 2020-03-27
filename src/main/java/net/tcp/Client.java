package net.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        //1,使用Socket创建客户端,需要指定服务器的地址和端口
        Socket client = null;
        try {
            client = new Socket("192.168.165.1",8888);
            //2,操作:输入输出流操作
            OutputStream stream = client.getOutputStream();
            DataOutputStream outputStream = new DataOutputStream(stream);
            outputStream.writeInt(1);
            outputStream.writeUTF("client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
