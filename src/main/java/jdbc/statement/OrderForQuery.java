package jdbc.statement;

import jdbc.bean.Order;
import jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;

public class OrderForQuery {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, SQLException {
        String sql = "select id,name,orderDate from Orders where id = ?";
        Order query = query(sql, 1);
        System.out.println(query);
    }

    public static Order query(String sql, Object ... args) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; ++i) {
            statement.setObject(i + 1, args[i]);
        }
        ResultSet resultSet = statement.executeQuery();
        //获取结果集的元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        //查询列数
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            Order order = new Order();
            for (int i = 0; i < columnCount; ++i) {
                Object object = resultSet.getObject(i + 1);
                String columnName = metaData.getColumnName(i + 1);
                Field field = Order.class.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(order, object);
            }
            return order;
        }
        return null;
    }
}
