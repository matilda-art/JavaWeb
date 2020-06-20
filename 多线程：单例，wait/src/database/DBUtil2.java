package database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: 多线程：单例，wait，阻塞队列
 * @description
 * @author: matilda
 * @create: 2020-06-20 16:50
 **/
public class DBUtil2 {
    //懒汉模式
    private static volatile DataSource dataSource = null;//DataSource指向唯一的一个对象

    private static DataSource initDataSource(){
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setServerName("127.0.0.1");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setCharacterEncoding("utf8");

        return mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null){
            synchronized (DBUtil.class){
                if (dataSource == null){
                    //volatile 可见性进一步限制了重排序的效果
                    dataSource = initDataSource();
                }
            }
        }
        return dataSource.getConnection();
    }
}
