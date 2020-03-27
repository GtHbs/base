package net.location;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 李昭
 */
public class IPTest {

    @Test
    public void test01() throws UnknownHostException {
        /**
         * 获取本机
         */
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());     //获取主机地址
        System.out.println(localHost.getHostName());        //获取主机名
        /**
         * 根据域名获取信息
         *      底层使用dns解析域名
         */
        InetAddress tencent = InetAddress.getByName("www.tencent.com");
        System.out.println(tencent.getHostAddress());
        System.out.println(tencent.getHostName());
        /**
         * 根据IP地址获取对象,一般情况下无权解析
         */
        InetAddress byName = InetAddress.getByName("113.142.52.246");
        System.out.println(byName.getHostName());       //获取的为IP
        System.out.println(byName.getHostAddress());
    }
}
