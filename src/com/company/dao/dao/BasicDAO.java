package com.company.dao.dao;

import com.company.jdbcutils.JDBCUtilsDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T> {

    private QueryRunner qr = new QueryRunner();

    //通用dml方法
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsDruid.close(null, null, connection);
        }
    }
    //返回多个对象(查询结果为多行)
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            return qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsDruid.close(null, null, connection);
        }
    }
    //查询单行结果
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            return qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsDruid.close(null, null, connection);
        }
    }
    //查询单行单列 返回单值
    public Object queryScanlar(String sql, int col, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            return qr.query(connection, sql, new ScalarHandler<>(col), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsDruid.close(null, null, connection);
        }
    }
}
