package com.company.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class Druid_ {
    public static void main(String[] args) throws Exception {
        new Druid_().testDruid();
    }
    public void testDruid() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
