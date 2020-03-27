package jdbc.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
    private volatile static ComboPooledDataSource instance;
    private C3P0() {}
    public static ComboPooledDataSource getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (ComboPooledDataSource.class) {
            if (instance == null) {
                /**
                 * 参数为配置文件config名
                 */
                instance = new ComboPooledDataSource("hello");
            }
        }
        return instance;
    }
}
