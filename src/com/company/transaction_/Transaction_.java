package com.company.transaction_;

import com.company.jdbcutils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction_ {
    public static void main(String[] args) {
        new Transaction_().useTransaction();
    }
    public void noTransaction() {
        Connection connection = JdbcUtils.getConnection();
        String sql = "update account set balance = balance - 100 where id = 100";
        String sql1 = "update account set balance = balance + 100 where id = 101";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, 100);
//            preparedStatement.setString(2, "御坂美琴123");
            preparedStatement.executeUpdate(sql);

            int i = 1/0;
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, preparedStatement, connection);
        }
    }
    public void useTransaction() {
        Connection connection = null;
        String sql = "update account set balance = balance - 100 where id = 100";
        String sql1 = "update account set balance = balance + 100 where id = 101";
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, 100);
//            preparedStatement.setString(2, "御坂美琴123");
            preparedStatement.executeUpdate(sql);

//            int i = 1/0;
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("sql异常");
        }
        catch (ArithmeticException e) {
            System.out.println("出现异常 回滚");
            //默认无参回滚到事务回滚的状态 参数可填保存点
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, preparedStatement, connection);
        }
    }
}
