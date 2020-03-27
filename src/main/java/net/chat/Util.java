package net.chat;

import java.io.Closeable;
import java.io.IOException;

public class Util {
    public static void release(Closeable ... target) {
        for(Closeable s : target) {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
