package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;

    private Database() { }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Here, we connect to an in-memory H2 database.
                connection = DriverManager.getConnection("jdbc:h2:mem:books;DB_CLOSE_DELAY=-1", "sa", "");
                connection.setAutoCommit(false); // For transaction management.
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
        }
        return connection;
    }

    public static void ensureConnectionIsOpen() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = null; // Make sure to reset the connection if it's closed.
                getConnection();  // Re-establish the connection.
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking or re-opening the database connection", e);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing the database connection", e);
            }
        }
    }
}
