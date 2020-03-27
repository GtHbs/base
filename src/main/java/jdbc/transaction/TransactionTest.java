package jdbc.transaction;

import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    public void testUpdate() {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql01 = "update orders set balance = 900 where id = ?";
            update(connection,sql01,1);
            //System.out.println(10 / 0);     //存在事务问题
            String sql02 = "update orders set balance = 1100 where id = ?";
            update(connection,sql02,2);
            connection.commit();
        }catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.close(connection);
        }
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
}
