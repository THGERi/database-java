package org.example;

import org.example.config.DatabaseConnection;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        DatabaseConnection.createBooksTable();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Example: Select all books from the database
            String query = "SELECT id, title FROM books";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String title = resultSet.getString("title");

                        // Create Book objects or do whatever you need with the data
                        Book book = new Book();

                        book.setTitle(title);

                        // Process the book
                        System.out.println(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}