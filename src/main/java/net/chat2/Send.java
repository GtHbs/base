package net.chat2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Enzo Cotter on 2020/2/3.
 */
public class Send implements Runnable {
    private Socket client;
    private String name;
    private DataOutputStream outputStream;
    private BufferedReader reader;
    private boolean isRunning;
    public Send(Socket client,String name) {
        this.name = name;
        this.client = client;
        this.isRunning = true;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            outputStream = new DataOutputStream(client.getOutputStream());
            send(name);
        } catch (IOException e) {
            release();
        }
    }

    private void release() {
        this.isRunning = false;
        Utils.release(reader,outputStream,client);
    }
    private void send(String msg) {
        try {
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            release();
        }
    }
    private String receive() {
        String msg = "";
        try {
            msg = reader.readLine();
        } catch (IOException e) {
            release();
        }
        return msg;
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
}
