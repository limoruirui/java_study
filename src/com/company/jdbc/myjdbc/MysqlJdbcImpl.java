package com.company.jdbc.myjdbc;

public class MysqlJdbcImpl implements JdbcInterface{
    @Override
    public Object getConnection() {
        System.out.println("mysql被连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成mysql的增删改查");
    }

    @Override
    public void close() {
        System.out.println("断开与mysql的连接");
    }
}
