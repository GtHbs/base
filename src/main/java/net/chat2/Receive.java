package net.chat2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {

    private Socket client;
    private DataInputStream inputStream;
    private boolean isRunning;
    public Receive(Socket client) {
        this.client = client;
        this.isRunning = true;
        try {
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            release();
        }
    }

    private void release() {
        this.isRunning = false;
        Utils.release(inputStream,client);
    }

    private String receive() {
        String msg = "";
        try {
            msg = inputStream.readUTF();
        } catch (IOException e) {
            release();
        }
        return msg;
    }

    @Override
    public void run() {
        while (isRunning) {
            String msg = receive();
            System.out.println(msg);
        }
    }
}
