package com.matilda;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: HTTP协议-Servlet基本API
 * @description
 * @author: matilda
 * @create: 2020-07-09 11:38
 **/
public class DBUtil {
    private static volatile DataSource dataSource = null;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null){
            synchronized (DBUtil.class){
                if (dataSource == null){
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }

    private static DataSource initDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setDatabaseName("day_0705");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf-8");

        return mysqlDataSource;
    }
}
