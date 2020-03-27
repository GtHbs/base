package jdbc.dao.junit;

import jdbc.bean.User;
import jdbc.dao.daoimpl.DaoImpl;
import jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UserTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnectionWithDruid();
        DaoImpl dao = new DaoImpl();
        List<User> all = dao.getAll(connection);
        User user = dao.select(connection, 2);
        System.out.println(user);
    }
}
