package jdbc.statement;

import jdbc.bean.User;
import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;

public class UserForQuery {

    @Test
    public void query01() {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select id,name,email,birth from user where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int anInt = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                User user = new User(anInt, name, email, date);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, connection, resultSet);
        }
    }


    public User query02(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
            }
            resultSet = statement.executeQuery();
            //获取元数据,其中包括结果集中的列数
            ResultSetMetaData metaData = statement.getMetaData();
            int count = metaData.getColumnCount();
            if (resultSet.next()) {
                User user = new User();
                for (int i = 0; i < count; ++i) {
                    Object value = resultSet.getObject(i + 1);
                    //获取字段列名
                    String name = metaData.getColumnName(i + 1);
                    //通过反射给类属性赋值
                    Field field = User.class.getDeclaredField(name);
                    field.setAccessible(true);      //设置可见性
                    field.set(user, value);
                }
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, connection, resultSet);
        }
        return null;
    }
}
