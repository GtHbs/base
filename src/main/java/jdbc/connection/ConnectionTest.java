package jdbc.connection;


import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Java与MySQL连接
 *
 * @author 李昭
 */
public class ConnectionTest {

    /**
     * 使用具体的实现类
     *
     * @throws SQLException
     */
    @Test
    public void testConnection01() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lz19961117");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    /**
     * 使用反射获取驱动类
     *
     * @throws Exception
     */
    @Test
    public void testConnection02() throws Exception {
        Class aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lz19961117");
        Connection connection = driver.connect(url, info);
        System.out.println(connection);
    }

    /**
     * 直接使用DriverManager获取连接对象
     *
     * @throws SQLException
     */
    @Test
    public void testConnection03() throws Exception {
        //注册驱动
        DriverManager.registerDriver((Driver)
                Class.forName("com.mysql.jdbc.Driver").newInstance());
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lz19961117");
        Connection connection = DriverManager.getConnection(url, info);
        System.out.println(connection);
    }

    /**
     * 减少不必要的代码,不需要进行注册
     *      static {
     *         try {
     *             java.sql.DriverManager.registerDriver(new Driver());
     *         } catch (SQLException E) {
     *             throw new RuntimeException("Can't register driver!");
     *         }
     *      }
     * 在使用反射加载类时已经注册过了
     * 也不需要使用反射加载驱动,因为在加入jar包时已经加载了驱动
     */
    @Test
    public void testConnection04() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");     //加载驱动类
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lz19961117");
        Connection connection = DriverManager.getConnection(url, info);
        System.out.println(connection);
    }

    /**
     * 以配置文件获取连接数据
     * @throws Exception
     */
    @Test
    public void testConnection05() throws Exception {
        InputStream stream = ConnectionTest.class.getClassLoader().
                getResourceAsStream("jdbc/jdbcConfig.properties");
        Properties properties = new Properties();
        properties.load(stream);
        String user = (String) properties.get("user");
        String password = (String) properties.get("password");
        String url = (String) properties.get("url");
        String driver = (String) properties.get("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
