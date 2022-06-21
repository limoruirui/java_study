package com.company.jdbcutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtilsDruid_use {
    public static void main(String[] args) {
        new JDBCUtilsDruid_use().testSelect();
    }
    public void testSelect() {
        Connection connection = null;
        String sql = "select * from student";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtilsDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                int chinese = set.getInt(3);
                int english = set.getInt(4);
                int math = set.getInt(5);
                System.out.println(id + "\t" + name + "\t" + chinese + "\t"
                + english + "\t" + math);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsDruid.close(set, preparedStatement, connection);
        }
    }
}
