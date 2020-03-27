package net.chat;

import java.io.*;
import java.net.Socket;

/**
 * 给服务器发送消息
 */
public class Send implements Runnable {
    private BufferedReader reader;          //读取控制台消息
    private DataOutputStream outputStream;  //给服务器发送消息
    private Socket client;                  ///客户端
    private boolean isRunning;              //当前线程是否继续执行
    private String name;                    //当前线程用户名
    public Send(Socket client,String name) {
        this.client = client;
        this.name = name;
        this.isRunning = true;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            outputStream = new DataOutputStream(client.getOutputStream());
            send(name);                     //把当前线程用户名发送给服务器
        } catch (IOException e) {
            release();
        }
    }
    //从控制台接收消息
    private String receive() {
        String msg = "";
        try {
            msg = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
    //给服务器发送消息
    public void send(String msg) {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            release();
        }
    }
    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!msg.trim().equals("")) {
                send(msg);
            }
        }
    }
    //释放资源
    private void release() {
        this.isRunning = false;
        Util.release(outputStream,client);
    }
}
