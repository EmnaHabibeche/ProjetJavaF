package com.system.projectmanagementsystem;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/base",USERNAME,PASSWORD );
    }
}
