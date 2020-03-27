package net.tcp;


import net.bean.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginMutiServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8888);
            Socket client = server.accept();
            System.out.println("客户端建立连接!!!");
            new Thread(new Channel(client)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Channel implements Runnable {
        private Socket client;
        private ObjectInputStream inputStream;
        private DataOutputStream outputStream;

        public Channel(Socket client) {
            this.client = client;
            try {
                inputStream = new ObjectInputStream(client.getInputStream());
                outputStream = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    client.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //接受客户端发来的数据
        private User receive() {
            User user = null;
            try {
                user = (User) inputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return user;
        }

        /**
         * 发送消息
         * @param msg
         * @return
         */
        private boolean send(boolean msg) {
            try {
                outputStream.writeBoolean(msg);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        /**
         * 释放资源
         */
        private void release() {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            User user = receive();
            if (user.getName().equals("alone") && user.getPassword().equals("123")){
                send(true);
            } else {
                send(false);
            }
            release();
        }
    }
}
