package net.tcp;

import net.bean.User;

import java.io.*;
import java.net.Socket;

public class LoginClient {
    public static void main(String[] args) {
        //1,使用Socket创建客户端,需要指定服务器的地址和端口
        Socket client = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            client = new Socket("192.168.165.1",8888);
            //2,操作:输入输出流操作
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("请输入用户名");
                String name = reader.readLine();
                System.out.print("请输入密码");
                String password = reader.readLine();
                User user = new User(name,password);
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(user);
                InputStream stream = client.getInputStream();
                DataInputStream inputStream = new DataInputStream(stream);
                boolean flag = inputStream.readBoolean();
                if (flag) {
                    System.out.println("登陆成功");
                    break;
                } else{
                    System.out.println("登陆失败");
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (client != null) {
                    client.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
