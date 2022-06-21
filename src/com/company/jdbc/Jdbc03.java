package com.company.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Jdbc03 {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        new Jdbc03().start1();
    }
    public void start1() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class<?> aClass = Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select * from test01";
        String sql1 = "insert into test01 values(null, '瑞瑞no')";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql1);
        if (i !=0) {
            System.out.println("添加成功");
        }
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            System.out.println(id + "\t" + name);
        }
        connection.close();
    }
}
