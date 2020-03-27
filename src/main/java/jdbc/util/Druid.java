package jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class Druid {
    private volatile static DataSource instance;

    private Druid() {}

    public static DataSource getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (DataSource.class) {
            if (instance == null) {
                Properties properties = new Properties();
                try {
                    properties.load(Thread.currentThread().getContextClassLoader().
                            getResourceAsStream("jdbc/dbcp.properties"));
                    instance = DruidDataSourceFactory.createDataSource(properties);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }
}
