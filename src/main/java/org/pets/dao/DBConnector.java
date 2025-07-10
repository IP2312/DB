package org.pets.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String url = "jdbc:mysql://localhost:3306/pets";
    private static String user = "root";
    private static String password = "9Y9tpOo2bBQpO5";

    public DBConnector() {
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
