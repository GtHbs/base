package jdbc.dao;

import jdbc.bean.User;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 提供表user的所有操作
 */
public interface UserDao {
    /**
     * 插入的统一操作
     *
     * @param connection
     * @param user
     */
    boolean insert(Connection connection, User user);

    /**
     * 统一的更新接口,根据id更新一个对象
     *
     * @param connection
     * @param user
     * @param id
     * @return
     */
    boolean update(Connection connection, User user, int id);

    /**
     * 根据id删除一条记录
     *
     * @param connection
     * @param id
     * @return
     */
    boolean delete(Connection connection, int id);

    /**
     * 根据id查询一条记录
     *
     * @param connection
     * @param id
     * @return
     */
    User select(Connection connection, int id);

    /**
     * 查询表中所有的记录
     *
     * @param connection
     * @return
     */
    List<User> getAll(Connection connection);

    /**
     * 查询表中记录数
     *
     * @param connection
     * @return
     */
    long getCount(Connection connection);

    /**
     * 查询表中最大生日
     *
     * @param connection
     * @return
     */
    Date getMaxBirth(Connection connection);
}

