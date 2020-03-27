package jdbc.dao.daoimpl;

import jdbc.bean.User;
import jdbc.dao.DAO;
import jdbc.dao.UserDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;


public class DaoImpl extends DAO<User> implements UserDao {

    @Override
    public boolean insert(Connection connection, User user) {
        String sql = "insert into user(name,email,birth)values(?,?,?)";
        operation(connection, sql, user.getName(), user.getEmail(), user.getBirth());
        return true;
    }

    @Override
    public boolean update(Connection connection, User user, int id) {
        String sql = "update user set name = ?,email = ?,birth = ? where id = ?";
        operation(connection, sql, user.getName(), user.getEmail(), user.getBirth(), id);
        return true;
    }

    @Override
    public boolean delete(Connection connection, int id) {
        String sql = "delete from user where id = ?";
        operation(connection, sql, id);
        return true;
    }

    @Override
    public User select(Connection connection, int id) {
        String sql = "select id,name,email,birth from user where id = ?";
        User user = select(connection, sql, id);
        return user;
    }

    @Override
    public List<User> getAll(Connection connection) {
        String sql = "select id,name,email,birth from user";
        List<User> all = getAllInstance(connection, sql);
        return all;
    }

    @Override
    public long getCount(Connection connection) {
        String sql = "select count(*) from user";
        Object value = getValue(connection, sql);
        return (long) value;
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from user";
        Object value = getValue(connection, sql);
        return (Date) value;
    }
}
