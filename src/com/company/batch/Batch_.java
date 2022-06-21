package com.company.batch;

import com.company.jdbcutils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Batch_ {
    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        new Batch_().noBatch();
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
    public void noBatch() throws Exception {
        Connection connection = JdbcUtils.getConnection();

        String sql = "insert into account values(null, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setDouble(2, 666);
            preparedStatement.executeUpdate();
        }
        JdbcUtils.close(null, preparedStatement, connection);
    }
    public void batch() throws Exception {
        Connection connection = JdbcUtils.getConnection();

        String sql = "insert into account values(null, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setDouble(2, 666);
            preparedStatement.addBatch();
            //当有1000条时再批量执行
            if ((i + 1) % 1000 ==0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        JdbcUtils.close(null, preparedStatement, connection);
    }
}
