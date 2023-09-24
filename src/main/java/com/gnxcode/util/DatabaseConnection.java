package com.gnxcode.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DatabaseConnection {
    private static String dbUrl = "jdbc:mysql://localhost:3306/project";
    private static String dbUser = "root";
    private static String dbPassword = "Mori*9010";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if(pool == null){
            pool = new BasicDataSource();
            pool.setUrl(dbUrl);
            pool.setUsername(dbUser);
            pool.setPassword(dbPassword);

            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
        }

        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
