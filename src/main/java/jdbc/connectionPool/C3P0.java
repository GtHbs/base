package jdbc.connectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 测试c3p0数据库连接池
 */
public class C3P0 {
    @Test
    public void testConnection() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream("jdbc/jdbcConfig.properties");
            Properties properties = new Properties();
            properties.load(stream);
            String user = (String) properties.get("user");
            String url = (String) properties.get("url");
            String password = (String) properties.get("password");
            String driver = (String) properties.get("driver");
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(password);

            dataSource.setInitialPoolSize(10);      //连接池初始化连接数
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            DataSources.destroy(dataSource);        //关闭数据库连接池
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取xml配置文件创建连接池
     */
    @Test
    public void testConnection02() throws SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource("hello");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
