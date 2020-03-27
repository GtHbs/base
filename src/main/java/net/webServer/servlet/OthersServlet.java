package net.webServer.servlet;

import net.webServer.Request;
import net.webServer.Response;
import net.webServer.Servlet;

public class OthersServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        System.out.println("Others");
    }
}
