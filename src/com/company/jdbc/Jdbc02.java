package com.company.jdbc;

import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//连接sql的五种方式
public class Jdbc02 {
    public static void main(String[] args) throws Exception {
        new Jdbc02().connect05();
    }
//    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://119.91.156.201:33154/db02";
        Properties properties = new Properties();
        properties.setProperty("user", "limoruirui");
        properties.setProperty("password", "misaka");

        //获取连接
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
        connect.close();
    }
    //反射
    public void connect02() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.getConstructor().newInstance();
        String url = "jdbc:mysql://119.91.156.201:33154/db02";
        Properties properties = new Properties();
        properties.setProperty("user", "limoruirui");
        properties.setProperty("password", "misaka");

        //获取连接
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
        connect.close();
    }
    //使用DriverManager 替代 driver
    public void connect03() throws Exception {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.getConstructor().newInstance();

        String url = "jdbc:mysql://119.91.156.201:33154/db02";
        String user = "limoruirui";
        String password = "misaka";

        DriverManager.registerDriver(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
        connection.close();
    }
    //DriverManager 自动 注册驱动
    //推荐使用
    public void connect04() throws Exception {
        //先用反射加载Driver类 让DriverManager加载好
        /*
        static {
            try {
                DriverManager.registerDriver(new Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
    */
        Class.forName("com.mysql.jdbc.Driver");//可写可不写 建议写上
        String url = "jdbc:mysql://119.91.156.201:33154/db02";
        String user = "limoruirui";
        String password = "misaka";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
        connection.close();
    }
    //在4的基础上 把账号密码信息从文件读取
    public void connect05() throws Exception {
        //先通过properties获取信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("./src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class<?> aClass = Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
        connection.close();
    }
}
