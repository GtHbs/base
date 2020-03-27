package net.chat;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名:");
        String name = reader.readLine();
        //1,使用Socket创建客户端,需要指定服务器的地址和端口
        Socket client = new Socket("192.168.165.1",8888);
        new Thread(new Send(client,name),name).start();       //发送消息线程
        new Thread(new Receive(client),name).start();    //接收消息线程
    }
}
