package net.webServer.utils;

import java.io.Closeable;
import java.io.IOException;

public class WebUtils {
    /**
     * 用于关闭连接
     *
     * @param target
     */
    public static void release(Closeable... target) {
        for (Closeable c : target) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
