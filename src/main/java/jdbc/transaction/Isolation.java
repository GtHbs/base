package jdbc.transaction;

import jdbc.bean.Order;
import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 测试数据库隔离级别
 * 1,读未提交read uncommit
 * 2,读已提交readcommit     解决脏读
 * 3,可重复度repeatedread   解决不可重复读
 * 4,串行化  serilizable    解决幻读
 * @author 李昭
 */
public class Isolation {


    @Test
    public void testTransactionSelect() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        //获取数据库事务隔离级别
        System.out.println(connection.getTransactionIsolation());
        /**
         * //设置数据库隔离级别
         * 1为读未提交
         * 2为读已提交
         * 4为可重复读
         * 8为串行化
         */
        String sql = "select name,orderDate,balance from orders where id = ?";
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        Thread.sleep(15000);
        Order order = select(connection, Order.class, sql, 1);
        connection.commit();
        System.out.println(order);
    }

    @Test
    public void testTransactionUpdate() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        String sql = "update orders set balance = 7000 where id = ?";
        update(connection,sql,1);
    }
    public void update(Connection connection,String sql,Object ... args) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1,args[i]);
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement);
        }
    }

    public <T>T select(Connection connection,Class<T> clazz,String sql,Object ... args) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1,args[i]);
            }
            set = statement.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            int count = metaData.getColumnCount();
            while (set.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < count; ++i) {
                    Object object = set.getObject(i + 1);
                    String label = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement,null,set);
        }
        return null;
    }
}
