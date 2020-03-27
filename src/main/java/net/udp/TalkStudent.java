package net.udp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkStudent {
    public static void main(String[] args) throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        /**
         * 第一个为当前端口,第二个为接收端ip,第三个为接收端端口
         */
        new Thread(new TalkSend(8888,localHost.getHostAddress(),6666)).start();
        //当前端口
        new Thread(new TalkReceive(7777),"老师").start();
    }
}
