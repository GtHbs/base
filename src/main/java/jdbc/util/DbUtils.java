package jdbc.util;

import jdbc.bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbUtils {

    @Test
    public void testInsert() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection connection = JDBCUtils.getConnectionWithDruid();
        String sql = "insert into user(name,email,birth)values(?,?,?)";
        Object[] param = {"diagnose", "ll@gmail.com", "2019-2-2"};
        runner.update(connection, sql, param);
    }

    @Test
    public void testQuery01() {
        try {
            QueryRunner runner = new QueryRunner();
            Connection connection = JDBCUtils.getConnectionWithDruid();
            String sql = "select id,name,email,birth from user where id = ?";
            BeanHandler<User> handler = new BeanHandler<>(User.class);
            User user = runner.query(connection, sql, handler, 3);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery02() {
        QueryRunner runner = null;
        Connection connection = null;
        try {
            runner = new QueryRunner();
            connection = JDBCUtils.getConnectionWithDruid();
            String sql = "select id,name,email,birth from user where id < ?";
            BeanListHandler<User> handler = new BeanListHandler<>(User.class);
            List<User> query = runner.query(connection, sql, handler, 5);
            query.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Test
    public void testQuery03() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnectionWithDruid();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from user where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> query = runner.query(connection, sql, handler, 5);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery04() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnectionWithDruid();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,name,email,birth from user where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(connection, sql, handler, 5);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Test
    public void testQuery05() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select count(*) from user";
            ScalarHandler<Object> handler = new ScalarHandler<>();
            long res = (long) runner.query(connection, sql, handler);
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Test
    public void testQuery06() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select max(birth) from user";
            ScalarHandler<Object> handler = new ScalarHandler<>();
            Date date = (Date) runner.query(connection, sql, handler);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }

    @Test
    public void testQuery07() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            ResultSetHandler<List<User>> handler = rs -> {
                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date birth = rs.getDate("birth");
                    list.add(new User(id,name,email,birth));
                }
                return list;
            };
            String sql = "select id,name,email,birth from user where id < ?";
            List<User> user = runner.query(connection, sql, handler,6);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection);
        }
    }
}
