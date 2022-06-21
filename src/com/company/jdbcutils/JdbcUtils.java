package com.company.jdbcutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
    //定义相关属性
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    //在static代码块初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/mysql.properties"));

        user = properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");
        driver = properties.getProperty("driver");
        } catch (IOException e) {
            //实际开发中 可以这样处理
            //1. 将编译异常转成运行异常
            //2.这时调用者 可以选择捕获该异常 也可以选择默认处理该异常 比较方便
            throw new RuntimeException(e);
        }
    }
    //连接数据库 返回connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    //关闭相关资源
//    1.ResultSet 结果集
//    2.Statement 或者 PreparedStatement
//    3.Connection
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
