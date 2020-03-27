package jdbc.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc.connection.ConnectionTest;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    /**
     * 获取连接
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("jdbc/jdbcConfig.properties");
            Properties properties = new Properties();
            properties.load(stream);
            String user = (String) properties.get("user");
            String password = (String) properties.get("password");
            String url = (String) properties.get("url");
            String driver = (String) properties.get("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //确保高并发下只创建一个数据源
    private static ComboPooledDataSource dataSource = C3P0.getInstance();

    public static Connection getConnectionWithC3P0() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    private static DataSource dbcpDataSource = jdbc.util.DBCP.getInstance();

    public static Connection getConnectionWithDBCP() throws SQLException {
        return dbcpDataSource.getConnection();
    }

    private static DataSource druidDataSource = Druid.getInstance();

    public static Connection getConnectionWithDruid() throws SQLException {
        return druidDataSource.getConnection();
    }

    /**
     * 关闭资源
     *
     * @param statement
     * @param connection
     */
    public static void close(Statement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement, Connection connection, ResultSet set) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (set != null) {
                set.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
