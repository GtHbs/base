package jdbc.statement;

import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class TestPreparedStatement {


    @Test
    public void insert() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long time = format.parse("2001-2-22").getTime();
        String sql = "insert into user (name,email,birth) values(?,?,?)";
        Object[] args = {"lj", "dsx@521.com", new Date(time)};
        composite(sql, args);
    }

    @Test
    public void update() {
        String sql = "update user set name = ? where id = ?";
        Object[] args = {"dsx", 1};
        composite(sql, args);
    }

    @Test
    public void delete() {
        String sql = "delete from user where id = ?";
        Object[] args = {1};
        composite(sql, args);
    }

    private void composite(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
            }
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, connection);
        }
    }


}
