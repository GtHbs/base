package jdbc.blob;

import jdbc.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

public class BlobTest {
    @Test
    public void insert() {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement statement = null;
        String sql = "insert into user(name ,email,birth,photo) values (?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1,"临安");
            statement.setObject(2,"tour@gmail.com");
            statement.setObject(3,"2003-10-10");
            statement.setBlob(4,
                    new FileInputStream("C:\\Users\\李昭\\Pictures\\ancient\\南宋临安.png"));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement,connection);
        }
    }


    @Test
    public void query() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select photo from user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1,4);
        ResultSet set = statement.executeQuery();
        while (set.next()) {
            Blob blob = set.getBlob("photo");
            InputStream stream = blob.getBinaryStream();
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\李昭\\Desktop\\q.jpg");
            byte[] bytes = new byte[1024];
            int c;
            while ((c = stream.read(bytes)) != -1) {
                outputStream.write(bytes,0,c);
            }
            outputStream.flush();
        }
    }
}
