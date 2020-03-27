package net.chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Enzo Cotter on 2020/2/3.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入您的姓名:");
        Socket client = new Socket("localhost",8888);
        String name = reader.readLine();
        new Thread(new Send(client,name)).start();
        new Thread(new Receive(client)).start();
    }
}
