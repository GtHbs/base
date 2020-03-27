package jdbc.batchop;

import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void main(String[] args) {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into commodity(name) values(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            long start = System.currentTimeMillis();
            /**
             * 批处理操作只有5.1.37及以下才支持
             */
            for (int i = 1; i <= 2000000; ++i) {
                statement.setObject(1,"commodit"+i);
                statement.addBatch();       //每积攒1000条sql执行一次
                if (i % 100000 == 0) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间为:"+(end - start) / 1000+"s");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert2() {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into commodity(name) values(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            long start = System.currentTimeMillis();
            /**
             * 批处理操作只有5.1.37及以下才支持
             */
            for (int i = 1; i <= 2000000; ++i) {
                statement.setObject(1,"commodit"+i);
                statement.addBatch();       //每积攒1000条sql执行一次
                if (i % 100000 == 0) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
            }
            connection.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间为:"+(end - start) / 1000+"s");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
