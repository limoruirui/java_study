package com.company.datasource;

import com.company.jdbcutils.JDBCUtilsDruid;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtils_USE {
    @Test
    public void testQueryMary() throws SQLException {
        Connection connection = JDBCUtilsDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String s = "select * from student";
        List<Student> list =
                queryRunner.query(connection, s, new BeanListHandler<>(Student.class));
        for (Student student : list) {
            System.out.println(student);
        }
        JDBCUtilsDruid.close(null, null, connection);
    }
    @Test
    public void testQuerySingle() throws SQLException {
        Connection connection = JDBCUtilsDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student";
        Student query = queryRunner.query(connection, sql, new BeanHandler<>(Student.class));
        System.out.println(query);
        JDBCUtilsDruid.close(null, null, connection);
    }
    @Test
    public void testScalar() throws SQLException {
        Connection connection = JDBCUtilsDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from student";
        Object query = queryRunner.query(connection, sql, new ScalarHandler<>(2));
        System.out.println(query);
        JDBCUtilsDruid.close(null,null, connection);
    }
    @Test
    public void testDML() throws SQLException{
        Connection connection = JDBCUtilsDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
//        String sql = "update student set name = ? where id = ?";
//        String sql = "insert into student values(?, ?, ?, ?, ?)";
        String sql = "delete from student where id = ?";
        int affectedRow = queryRunner.update(connection, sql, 6);
        System.out.println(affectedRow);
        JDBCUtilsDruid.close(null, null, connection);
    }
}
