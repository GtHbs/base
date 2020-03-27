package jdbc.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DBCP {
    private volatile static DataSource instance;

    private DBCP() {}
    public static DataSource getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (DataSource.class) {
            Properties properties = new Properties();
            try {
                properties.load(Thread.currentThread().getContextClassLoader().
                        getResourceAsStream("jdbc/dbcp.properties"));
                instance = BasicDataSourceFactory.createDataSource(properties);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
