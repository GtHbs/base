package net.tcp;

import net.bean.User;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class LoginMutiClient {
    public static void main(String[] args) {
        Socket client = null;
        BufferedReader reader = null;
        try {
            client = new Socket("localhost",8888);
            String name = "";String password = "";
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入姓名");
            name = reader.readLine();
            System.out.println("请输入密码");
            password = reader.readLine();
            User user = new User(name,password);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(user);
            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            boolean flag = inputStream.readBoolean();
            if (flag) {

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void release() {

    }
    static class Send {
        private Socket client;
        public Send(Socket client) {
            this.client = client;

        }

    }
}
