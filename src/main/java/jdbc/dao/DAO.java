package jdbc.dao;


import jdbc.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

    private Class<T> clazz = null;

    {
        //这个this为实现类的this
        Type type = this.getClass().getGenericSuperclass();     //父类的泛型
        ParameterizedType paramType = (ParameterizedType) type;
        Type[] types = paramType.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    /**
     * 通用增删改
     *
     * @param connection
     * @param sql
     * @param args
     */
    public void operation(Connection connection, String sql, Object... args) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement);
        }
    }

    public T select(Connection connection, String sql, Object... args) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
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
                    field.set(t, object);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, null, set);
        }
        return null;
    }

    /**
     * 查询多个对象
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T> getAllInstance(Connection connection,String sql, Object... args) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1, args[i]);
            }
            set = statement.executeQuery();
            ResultSetMetaData metaData = set.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<T> list = new ArrayList<>();
            while (set.next()) {
                T t = (T) clazz.newInstance();
                for (int i = 0; i < columnCount; ++i) {
                    Object object = set.getObject(i + 1);
                    String label = metaData.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(label);
                    field.setAccessible(true);
                    field.set(t, object);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement, null, set);
        }
        return null;
    }

    public <E> E getValue(Connection connection,String sql,Object ... args) {
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; ++i) {
                statement.setObject(i + 1,args[i]);
            }
            set = statement.executeQuery();
            if (set.next()) {
                return (E) set.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(statement,null,set);
        }
        return null;
    }
}
