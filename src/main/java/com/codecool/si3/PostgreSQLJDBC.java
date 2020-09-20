package com.codecool.si3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver;

public class PostgreSQLJDBC {
    private final String HOST = "//127.0.0.1";
    private final String PORT = ":5432";
    private final String DB_NAME = "/SI3";
    private final String SSLMODE = "";
    private final String CONNECTION_STRING = "jdbc:postgresql:" + HOST + PORT + DB_NAME + SSLMODE;
    private final String USER = "postgres";
    private final String PASSWORD = "#piratkali#1080#";
    private Connection connection = null;
    private static PostgreSQLJDBC JDBCInstance;

    static PostgreSQLJDBC getInstance(){
        if (JDBCInstance == null) JDBCInstance = new PostgreSQLJDBC();
        return JDBCInstance;
    }

    public Connection connect() {
        try {
            this.connection = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
            System.out.println("Opened database successfully");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return connection;
    }

    public void disconnect(){
        try {
            connection.close();
            if(connection.isClosed()){
                System.out.println("DB Closed");
            }else {
                System.out.println("Cant't Close DB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}