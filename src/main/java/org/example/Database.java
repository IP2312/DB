package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/pets";
    private static String user = "root";
    private static String password = "9Y9tpOo2bBQpO5";

    private Database(){

    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

//    public static void closeConnection(Connection connection) throws SQLException {
//        connection.close();
//    }

//    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
//        preparedStatement.close();
//    }

}
