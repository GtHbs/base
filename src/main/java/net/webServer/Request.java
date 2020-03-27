package net.webServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * 封装请求协议
 */
@SuppressWarnings("all")
public class Request {

    private InputStream stream;                 //请求输入流
    private String requestInfo;                 //协议信息
    private String method;                      //请求方法
    private String url;                         //请求的url
    private String queryParam;                  //请求参数
    private final String CL = "\r\n";           //换行符
    private Map<String, List<String>> paramMap; //保存请求参数

    public Request(Socket client) throws Exception {
        this(client.getInputStream());
    }

    public Request(InputStream stream) throws Exception {
        this.stream = stream;
        this.paramMap = new HashMap<>();
        byte[] content = new byte[1024 * 1024 * 1024];     //请求信息最大为1G
        try {
            //将请求信息读取到字节数组中
            int len = stream.read(content);
            /**
             * 封装请求信息
             */
            this.requestInfo = new String(content, 0, len);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //解析请求信息
        parseRequestInfo();
    }

    /**
     * 解析请求字符串
     */
    private void parseRequestInfo() throws Exception {
        this.method = requestInfo.substring(0, requestInfo.indexOf("/")).toLowerCase().trim();
        /**
         * 请求信息一般格式为
         * /url?param HTTP:/1.0
         */
        this.url = requestInfo.substring(requestInfo.indexOf("/") + 1, requestInfo.indexOf("HTTP/")).trim();
        /**
         * 判断uri后面是否存在请求参数
         */
        int i = url.indexOf("?");
        if (i >= 0) {       //存在请求参数
            String[] urlArray = this.url.split("\\?");
            this.url = urlArray[0].trim();
            this.queryParam = urlArray[1].trim();
        }
        /**
         * post方式,则请求参数可能在uri后面也可能在请求正文最后面
         */
        if (method.equals("post")) {
            //获取请求正文最后面的请求参数
            String str = this.requestInfo.substring(requestInfo.lastIndexOf(CL)).trim();
            if (null == queryParam) {
                queryParam = str;
            } else {
                //请求参数之间以&分隔
                queryParam += "&" + str;
            }
        }
        //避免空指针异常
        queryParam = null == queryParam ? "" : queryParam;
        //将请求参数使用map封装
        ConvertMap();
    }

    /**
     * 将请求参数全部封装在map中
     *
     * @throws Exception
     */
    private void ConvertMap() throws Exception {
        /**
         * uname=alone&age=20
         */
        String[] values = this.queryParam.split("&");
        for (String s : values) {
            /**
             * 将参数名与参数值分割
             */
            String[] v = s.split("=");
            /**
             * 因为存在有的参数名的参数值为null,将其补充为空串
             */
            v = Arrays.copyOf(v, 2);
            String key = v[0];
            //处理中文乱码问题,避免空指针异常
            String value = v[1] == null ? "" : decode(v[1], "UTF-8");
            /**
             * 查看map中是否存在该key,如果存在直接添加,否则插入
             */
            if (paramMap.containsKey(key)) {
                paramMap.get(key).add(value);
            } else {
                paramMap.put(key, new ArrayList<>());
                paramMap.get(key).add(value);
            }
        }
    }

    /**
     * 处理中文乱码问题
     *
     * @param value 请求参数值
     * @param enc   编码格式
     * @return
     * @throws Exception
     */
    private String decode(String value, String enc) throws Exception {
        return java.net.URLDecoder.decode(value, enc);
    }

    /**
     * 获取请求参数值,一个参数可能存在多个值
     *
     * @param key
     * @return
     */
    public String[] getParameterValues(String key) {
        List<String> list = paramMap.get(key);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.toArray(new String[0]);
    }

    /**
     * 获取请求参数的第一个值
     *
     * @param key
     * @return
     */
    public String getParameterValue(String key) {
        String[] values = getParameterValues(key);
        return values == null ? "" : values[0];
    }

    /**
     * 获取请求参数
     *
     * @return
     */
    public String getQueryParam() {
        return queryParam;
    }

    /**
     * 获取请求方式
     *
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * 获取uri
     *
     * @return
     */
    public String getUrl() {
        return url;
    }
}
