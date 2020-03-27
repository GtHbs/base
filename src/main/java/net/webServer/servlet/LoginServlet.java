package net.webServer.servlet;

import net.webServer.Request;
import net.webServer.Response;
import net.webServer.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response responses) {
        responses.print("<html>");
        responses.print("<meta charset='UTF-8'>");
        responses.print("<head>");
        responses.print("<title>");
        responses.print("登陆成功!!!");
        responses.print("</title>");
        responses.print("</head>");
        responses.print("<body>");
        responses.print("登陆成功" + request.getParameterValue("uname"));
        responses.print("</body>");
        responses.print("</html>");
    }
}
