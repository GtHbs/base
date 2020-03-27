package jdbc.connectionPool;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class Druid {


    private static DruidDataSource dataSource = (DruidDataSource) jdbc.util.Druid.getInstance();

    @Test
    public void getConnection() throws SQLException {
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
