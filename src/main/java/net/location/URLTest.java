package net.location;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL(Unique Resource Location):统一资源定位符
 *  1,协议
 *  2,域名
 *  3,端口
 *  4,资源
 */
public class URLTest {

    @Test
    public void test01() throws MalformedURLException {
        URL url = new URL("http://www.baidu.com:80/index.html?name=ss&age=10#a");
        System.out.println("protocolType:"+url.getProtocol());
        System.out.println("IP:"+url.getHost());
        System.out.println("Resource:"+url.getFile());
        System.out.println("Resource:"+url.getPath());
        System.out.println("Port:"+url.getPort());
        System.out.println("Parameter:"+url.getQuery());
        System.out.println("Ancher:"+url.getRef());
    }
}
