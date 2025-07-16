package org.pets.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String url = "jdbc:mysql://localhost:3306/pets";
    private static String user = "root";
    private static String password = "9Y9tpOo2bBQpO5";
    private static DBConnector connector = null;
    private static Connection connection = null;

    public DBConnector() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("faild to connect to DB", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connector == null) {
            connector = new DBConnector();
        } else if (connector.connection.isClosed()) {
            connector = new DBConnector();

        }
        return connector.connection;
    }
}
