package com.company.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
// 静态加载
public class Jdbc01 {
    public static void main(String[] args) throws Exception{
        //导入jar包 加入库中
        //1.注册驱动
        Driver driver = new Driver();

        //2.得到连接
        String url = "jdbc:mysql://119.91.156.201:33154/db02";
        //将用户名和密码放入到Properties对象
        Properties properties = new Properties();
        properties.setProperty("user", "limoruirui");
        properties.setProperty("password", "misaka");

        //获取连接
        Connection connect = driver.connect(url, properties);

        //3.执行sql
        String sql = "insert into test01 values(null, 'ruirui')";
        //创建一个
        Statement statement = connect.createStatement();
        int i = statement.executeUpdate(sql); //返回的是受影响的行数 失败则为0 成功则为大于0
        System.out.println(i > 0 ? "成功" : "失败");

        //关闭连接
        statement.close();
        connect.close();
    }
}
