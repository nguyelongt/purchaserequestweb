package com.prs.Utilities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/PRS";

    private static Connection connection;

    public static synchronized Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
            System.out.println("Connection Status: Connected");
        } catch (SQLException e) {
            System.out.println("Error establishing connection!");
            throw e;
        }
        catch (IllegalAccessException|InstantiationException|ClassNotFoundException me) {
            System.err.println("exception in DBUtil...");
            me.printStackTrace();
        }
        return connection;
    }

    // Not yet use
    // Future release
    public static void closeConnection() throws SQLException {

        try {
            connection.close();
            System.out.println("Connection Status: Closed");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }



}
