package org.example.mvc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static {
        try {
            Class.forName(ConfigUtil.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("JDBC Driver not found: " + e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url  = ConfigUtil.getProperty("db.url");
        String user = ConfigUtil.getProperty("db.user");
        String pass = ConfigUtil.getProperty("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}

