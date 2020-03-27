package net.location;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络爬虫基础
 */
public class SpiderTest {

    @Test
    public void test() throws IOException {
        URL url = new URL("https://www.dianping.com");
        InputStream stream = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream,"UTF-8"));
        String str;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        reader.close();
    }

    @Test
    public void test02() throws Exception {
        URL url = new URL("https://www.jd.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("get");
        connection.setRequestProperty("User-Agent","");

    }
}
