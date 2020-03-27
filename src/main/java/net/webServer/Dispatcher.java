package net.webServer;

import net.webServer.parse.WebApp;
import net.webServer.utils.WebUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 转发器
 */
@SuppressWarnings("all")
public class Dispatcher implements Runnable {

    /**
     * 每个客户端连接
     */
    private Socket client = null;
    /**
     * 客户端请求
     */
    private Request request = null;
    /**
     * 给客户端响应
     */
    private Response response = null;

    public Dispatcher(Socket client) {
        this.client = client;
        try {
            request = new Request(client);
            response = new Response(client);
        } catch (Exception e) {
            WebUtils.release(client);
        }
    }

    /**
     * 多线程处理并发问题
     */
    @Override
    public void run() {
        try {
            /**
             * url为空,找不到servlet返回404
             */
            if (request.getUrl().equals("") || request.getUrl() == null) {
                response.pushTBrowser(404);
                return;
            }
            Servlet servlet = WebApp.getServlet(request.getUrl());
            if (servlet != null) {
                //面向接口编程
                servlet.service(request, response);
                response.pushTBrowser(200);        //消息推送到浏览器
            } else {
                response.print("<html>");
                response.print("<meta charset='UTF-8'>");
                response.print("<head>");
                response.print("<title>");
                response.print("404");
                response.print("</title>");
                response.print("</head>");
                response.print("<body>");
                response.print("页面找不到了");
                response.print("</body>");
                response.print("</html>");
                response.pushTBrowser(404);
            }
        } catch (Exception e) {
            response.pushTBrowser(505);
        } finally {
            WebUtils.release(client);
        }
    }
}
