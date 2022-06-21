package com.company.statement;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStaterment_ {
    public static void main(String[] args) throws Exception {
        test();
    }
    public static void test() throws Exception {
        Scanner scanner = new Scanner(System.in);

        //输入用户名和密码
        System.out.print("请输入账号id: ");


        String  admin_id = scanner.nextLine();
        System.out.print("请输入名字: ");
        String admin_pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class<?> aClass = Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);


        //sql语句的?相当于占位符
//        String sql = "select id, name from test01 where id=? and name =?";
//        String sql = "insert into test01 values(?, ?)"; //增加
        String sql = "delete from test01 where name = ?"; //删除
//        String sql = "update test01 set name = ? where id = ?"; //修改
//        String sql1 = "insert into test01 values(null, '瑞瑞no')";
//        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给占位符赋值
//        preparedStatement.setNull(1, Types.NULL);
//        preparedStatement.setString(0, admin_id);
        preparedStatement.setString(1, admin_pwd);
//        int i = statement.executeUpdate(sql1);
//        if (i !=0) {
//            System.out.println("添加成功");
//        }

//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            System.out.println("success");
//        } else {
//            System.out.println("error");
//        }
        int i = preparedStatement.executeUpdate();
        if (i != 0 ) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            System.out.println(id + "\t" + name);
//        }
//        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
