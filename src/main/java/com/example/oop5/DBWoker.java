package com.example.oop5;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWoker {
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/users";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private Connection connection;
    public DBWoker() {
        try {
            try{
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("verno");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
