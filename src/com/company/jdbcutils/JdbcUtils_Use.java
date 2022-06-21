package com.company.jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils_Use {
    public static void main(String[] args) {
        new JdbcUtils_Use().testSelect();
    }

    public void testSelect() {
        Connection connection = JdbcUtils.getConnection();

        String sql = "select * from test01";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet);
            while (resultSet.next()) {
//                System.out.println(777);
                System.out.print(resultSet.getInt(1) + "\t");
                System.out.println(resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }
    }
    public void testDML() {
        Connection connection = JdbcUtils.getConnection();
        String sql = "update test01 set id = ? where name = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 100);
            preparedStatement.setString(2, "御坂美琴123");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, preparedStatement, connection);
        }
    }
}
