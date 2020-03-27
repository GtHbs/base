package net.webServer;

import net.webServer.parse.WebApp;
import net.webServer.servlet.LoginServlet;
import net.webServer.servlet.RegisterServlet;
import net.webServer.utils.WebUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 使用ServerSocket获取与浏览器的连接,获取协议内容
 */
@SuppressWarnings("all")
public class Server {

    private ServerSocket serverSocket = null;       //服务端主线程
    private boolean isRunning = false;               //服务端启动标志

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 启动服务
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(8888);
            this.isRunning = true;
            receive();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败...");
            stop();
        }
    }

    /**
     * 接受连接处理
     */
    public void receive() {
        while (isRunning) {
            try {
                /**
                 * 阻塞接受客户端的请求
                 * client中保存了客户端传来的所有信息
                 */
                Socket client = serverSocket.accept();
                Dispatcher dispatcher = new Dispatcher(client);
                //多线程分发启动
                new Thread(dispatcher).start();
            } catch (IOException e) {
                System.out.println("客户端连接出现问题...");
            }
        }
    }

    /**
     * 结束服务
     */
    public void stop() {
        this.isRunning = false;
        WebUtils.release(serverSocket);
    }
}
