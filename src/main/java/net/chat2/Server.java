package net.chat2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {


    private static CopyOnWriteArrayList<Channel> list = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            Channel channel = new Channel(client);
            list.add(channel);
            new Thread(channel).start();
        }
    }

    /**
     * 服务器接收客户端类
     */
    static class Channel implements Runnable {
        private DataInputStream inputStream;
        private DataOutputStream outputStream;
        private Socket client;
        private boolean isRunning;
        private String name;

        public Channel(Socket client) {
            this.client = client;
            this.isRunning = true;
            try {
                inputStream = new DataInputStream(client.getInputStream());
                outputStream = new DataOutputStream(client.getOutputStream());
                this.name = receive();
                sendAll(name+"进入聊天室",true);
            } catch (IOException e) {
                release();
            }
        }

        private void release() {
            this.isRunning = false;
            Utils.release(inputStream,outputStream,client);
            list.remove(this);
            sendAll(name+"退出了聊天室",true);
        }

        private void send(String msg) {
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                release();
            }
        }
        private void sendAll(String msg,boolean flag) {
            boolean pri = msg.startsWith("@");
            if (pri) {      //私聊
                int index = msg.indexOf(":");
                String target = msg.substring(1,index);
                msg = msg.substring(index + 1);
                for (Channel channel : list) {
                    if (channel.name.equals(target)) {
                        channel.send(name+"对你说"+msg);
                        break;
                    }
                }
            } else {
                for (Channel channel : list) {
                    if (channel == this) {
                        continue;
                    } else {
                        if (flag) {
                            channel.send("公告"+msg);
                        } else {
                            channel.send(name+":"+msg);
                        }
                    }
                }
            }
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
                if (!msg.trim().equals("")) {
                    sendAll(msg,false);
                }
            }
        }
    }
}
