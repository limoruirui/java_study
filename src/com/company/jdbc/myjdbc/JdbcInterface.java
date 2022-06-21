package com.company.jdbc.myjdbc;

public interface JdbcInterface {
    //连接数据库
    public Object getConnection() ;
    //crud
    public void crud();
    //关闭连接
    public void close();
}
