package jdbc.connectionPool;


import org.apache.commons.dbcp.BasicDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBCP {

    @Test
    public void getConnectionWithSet() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setPassword("lz19961117");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(10);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    private static DataSource dataSource = jdbc.util.DBCP.getInstance();

    @Test
    public void getConnectionWithFile() throws Exception {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
