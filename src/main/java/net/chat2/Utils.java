package net.chat2;

import java.io.Closeable;
import java.io.IOException;

/**
 * 释放资源类
 */
public class Utils {
    public static void release(Closeable ... target) {
        for (Closeable closeable : target) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
