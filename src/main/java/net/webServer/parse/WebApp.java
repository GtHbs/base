package net.webServer.parse;

import net.webServer.Servlet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;

/**
 * 解析web.xml文件
 */
@SuppressWarnings("all")
public class WebApp {
    /**
     * web.xml所有的信息都保存在此类中
     */
    private static WebContent content = null;

    static {
        try {
            /**
             * 获得解析工厂
             */
            SAXParserFactory factory = SAXParserFactory.newInstance();
            /**
             * 获得解析器
             */
            SAXParser parser = factory.newSAXParser();
            WebHandler handler = new WebHandler();
            /**
             * 解析web.xml文件
             */
            parser.parse(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("net/webServer/file/web.xml"), handler);
            content = new WebContent(handler.getEntities(), handler.getMappings());
        } catch (Exception e) {
            System.out.println("配置文件错误!!!");
        }
    }


    /**
     * 通过url获取配置文件对应的类
     *
     * @param url
     * @return
     */
    public static Servlet getServlet(String url) {
        String className = content.getClass("/" + url);
        Servlet servlet = null;
        try {
            /**
             * 通过反射获取类
             */
            Class<?> aClass = Class.forName(className);
            servlet = (Servlet) aClass.getConstructor().newInstance();
        } catch (Exception e) {
        }
        return servlet;
    }
}
