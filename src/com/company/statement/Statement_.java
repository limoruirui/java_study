package com.company.statement;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Statement_ {
    public static void main(String[] args) throws Exception {
        test();
    }
    public static void test() throws Exception {
        Scanner scanner = new Scanner(System.in);

        //输入用户名和密码
        System.out.print("请输入账号id: ");


        String admin_id = scanner.nextLine();
        System.out.print("请输入账号id: ");
        String admin_pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class<?> aClass = Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        String sql = "select id, name from test01 where id='" +
                admin_id + "' and name = '" + admin_pwd + "'";
//        String sql1 = "insert into test01 values(null, '瑞瑞no')";
        Statement statement = connection.createStatement();
//        int i = statement.executeUpdate(sql1);
//        if (i !=0) {
//            System.out.println("添加成功");
//        }
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("success");
        } else {
            System.out.println("error");
        }
//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            System.out.println(id + "\t" + name);
//        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
