package net.chat;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    //在复制的时候写
    private static CopyOnWriteArrayList<Channel> clients = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            Channel c = new Channel(client);
            clients.add(c);     //将所有的客户端线程装入到容器中
            //每来一个客户开启一个线程
            new Thread(c).start();
        }
    }

    //一个客户代表一个channel
    static class Channel implements Runnable {
        private DataInputStream inputStream = null;
        private DataOutputStream outputStream = null;
        private Socket client;
        private boolean isRunning;
        private String name;
        public Channel(Socket client) {
            this.client = client;
            try {
                inputStream = new DataInputStream(client.getInputStream());
                outputStream = new DataOutputStream(client.getOutputStream());
                this.name = receive();
                sendOthers(name+"进入聊天室!!!",true);
            } catch (IOException e) {
                release();
            }
            this.isRunning = true;
        }

        //1,接收客户端消息
        private String receive() {
            String data = "";
            try {
                data = inputStream.readUTF();
            } catch (IOException e) {
                System.out.println("------1------");
                release();
            }
            return data;
        }

        //2,给客户端发送消息
        private void send(String msg) {
            try {
                outputStream.writeUTF(msg);
                outputStream.flush();
            } catch (IOException e) {
                System.out.println("--------2----------");
                release();
            }
        }

        //将在自己的消息发送给其他人
        private void sendOthers(String msg,boolean isSys) {
            boolean isPrivate = msg.startsWith("@");
            if (isPrivate) {
                int index = msg.indexOf(":");
                String target = msg.substring(1,index);
                msg = msg.substring(index+1);
                for (Channel c : clients) {
                    if (c.name.equals(target)){
                        c.send(name+"对你说:"+msg);
                        break;
                    }
                }
            } else {
                for (Channel c : clients) {
                    if (c == this){
                        continue;
                    }
                    if (isSys) {
                        c.send("公告:"+msg);
                    } else {
                        c.send(name+":"+msg);
                    }
                }
            }
        }

        //3,释放资源
        private void release() {
            this.isRunning = false;
            Util.release(inputStream, outputStream, client);
            clients.remove(this);
            sendOthers(name+"退出了群聊",true);
        }

        @Override
        public void run() {
            while (isRunning) {
                String msg = receive();
                if (!msg.trim().equals("")) {
                    sendOthers(msg,false);
                }
            }
        }
    }
}
