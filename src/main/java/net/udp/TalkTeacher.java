package net.udp;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TalkTeacher {
    public static void main(String[] args) throws SocketException, UnsupportedEncodingException, UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        new Thread(new TalkReceive(6666),"学生").start();      //接受
        new Thread(new TalkSend(9999,localHost.getHostAddress(),7777)).start();
    }
}
