package com.gnxcode.util;

import java.sql.*;

public class DatabaseConnection {
    private static String dbUrl = "jdbc:mysql://localhost:3306/project";
    private static String dbUser = "root";
    private static String dbPassword = "Mori*9010";
    private static Connection myConn;

    public static Connection getInstance() throws SQLException {
        if(myConn == null){
            myConn = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        }

        return myConn;
    }
}
