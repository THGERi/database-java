package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    // jdbc:mysql://localhost:${DB_PORT}/${DB_URL}?createDatabaseIfNotExist=true&useSSL=true
    private static final String URL = "jdbc:mysql://localhost:3306/first_database?createDatabaseIfNotExist=true&useSSL=true";
    private static final String USER = "root";
    private static final String PASSWORD = "1788";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
    public static void createBooksTable() {

        try (Connection connection = getConnection()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS books ("
                    + "id INT PRIMARY KEY,"
                    + "title VARCHAR(255) NOT NULL"
                    + ")";
            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating 'books' table", e);
        }
    }
}