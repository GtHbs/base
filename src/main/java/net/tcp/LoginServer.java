package net.tcp;

import net.bean.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            //1,使用ServerSocket创建
            server = new ServerSocket(8888);    //服务器端口
            //2,阻塞式等待连接
            Socket client = server.accept();        //获取客户端连接
            boolean flag = false;
            while (!flag) {
                //3,操作:输入输出流操作
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                User user = (User) objectInputStream.readObject();

                if (user.getName().equals("alone") && user.getPassword().equals("123")){
                    System.out.println("登录成功!!");
                    System.out.println(user);
                    flag = true;
                } else {
                    System.out.println("用户名或密码错误!!");
                    flag = false;
                }
                DataOutputStream stream = new DataOutputStream(client.getOutputStream());
                stream.writeBoolean(flag);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
