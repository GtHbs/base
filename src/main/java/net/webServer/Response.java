package net.webServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 封装响应信息
 */
@SuppressWarnings("all")
public class Response {

    private BufferedWriter writer;          //写出到浏览器的输出流
    private StringBuilder content;          //正文
    private StringBuilder head;             //协议头信息
    private int length;                     //正文字节数
    private final String BLANK = " ";       //空格
    private final String CL = "\r\n";       //换行符

    public Response() {
        content = new StringBuilder();
        head = new StringBuilder();
        length = 0;
    }

    public Response(OutputStream stream) {
        this();
        writer = new BufferedWriter(new OutputStreamWriter(stream));
    }

    public Response(Socket client) {
        this();
        try {
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            head = null;
        }
    }

    /**
     * 向正文中添加字符
     *
     * @param info
     * @return
     */
    public Response print(String info) {
        content.append(info);
        length += info.getBytes().length;
        return this;
    }


    /**
     * 根据状态码构建头信息
     *
     * @param code
     */
    private void createHeadInfo(int code) {
        head.append("HTTP:/1.1").append(BLANK);
        head.append(code).append(BLANK);
        switch (code) {
            case 200:           //正常
                head.append("OK").append(CL);
                break;
            case 404:           //资源未找到
                head.append("NOT FOUND").append(CL);
                break;
            case 505:           //发生错误
                head.append("SERVER ERROR").append(CL);
                break;
        }
        /**
         * 拼接响应信息头
         */
        head.append("Date:").append(new Date()).append(CL);
        head.append("Server:").append("dsx Server/0.0.1;charset=UTF-8").append(CL);
        head.append("Content-type:text/html").append(CL);
        head.append("Content-length:").append(length).append(CL);
        head.append(CL);
    }

    /**
     * 将消息推送到浏览器
     *
     * @param code
     */
    public void pushTBrowser(int code) {
        if (head == null) {
            code = 505;
        }
        createHeadInfo(code);       //创建消息头
        try {
            /**
             * 将消息返回到浏览器
             */
            writer.write(head.toString());
            writer.write(content.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
