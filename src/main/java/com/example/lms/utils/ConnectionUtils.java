package com.example.lms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib_mngmt", "root", "arvind");
        } catch (SQLException e) {
            e.printStackTrace();
        }   catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
