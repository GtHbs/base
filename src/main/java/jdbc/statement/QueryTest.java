package jdbc.statement;

import jdbc.bean.Order;
import jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 针对不同表的通用查询
 */
public class QueryTest {
    public static void main(String[] args) {
        String sql = "select id,name,orderDate from Orders where id <= ?";
        QueryTest test = new QueryTest();
        List<Order> list = test.getAllInstance(Order.class, sql, 2);
        list.forEach(System.out::println);
    }
    public static  <T> T getInstance(Class<T> tClass,String sql,Object ... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1,args[i]);
            }
            set = statement.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (set.next()) {
                T t = tClass.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    Object object = set.getObject(i + 1);
                    String label = metaData.getColumnLabel(i + 1);
                    Field field = tClass.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.close(statement,connection,set);
        }
        return null;
    }


    public <T> List<T> getAllInstance(Class<T> clazz, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1,args[i]);
            }
            set = statement.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<T> list = new ArrayList<>();
            while (set.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    Object object = set.getObject(i + 1);
                    String label = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.close(statement,connection,set);
        }
        return null;
    }
}
