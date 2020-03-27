package net.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 从服务器接受消息
 */
public class Receive implements Runnable {
    private DataInputStream inputStream;
    private Socket client;
    private boolean isRunning;
    public Receive(Socket client) {
        this.isRunning = true;
        this.client = client;
        try {
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
        }
    }
    //从服务器接收消息
    private String receive() {
        String msg = "";
        try {
            msg = inputStream.readUTF();
        } catch (IOException e) {
            release();
        }
        return msg;
    }
    //释放资源
    private void release() {
        this.isRunning = false;
        Util.release(inputStream,client);
    }
    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            if (!msg.trim().equals("")) {
                System.out.println(msg);
            }
        }
    }
}
