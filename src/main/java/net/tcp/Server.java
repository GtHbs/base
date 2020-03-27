package net.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            //1,使用ServerSocket创建
            server = new ServerSocket(8888);    //服务器端口
            //2,阻塞式等待连接
            Socket client = server.accept();        //获取客户端连接
            //3,操作:输入输出流操作
            InputStream stream = client.getInputStream();
            DataInputStream inputStream = new DataInputStream(stream);
            int i = inputStream.readInt();
            String s = inputStream.readUTF();
            System.out.println(i + " " + s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4,释放资源
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
