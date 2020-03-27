package net.location;

import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

/**
 * 端口:
 *  1,用于区分软件
 *  2,占用2个字节 0-65535    TCP,udp
 *  3,同一个协议下不能冲突
 *
 * @author 李昭
 */
public class PortTest {

    @Test
    public void test01() {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",8080);
        InetSocketAddress address1 = new InetSocketAddress("localhost",3306);
        System.out.println(address1.getHostName());
        System.out.println(address1.getAddress());
        System.out.println(address.getPort());
    }
}
