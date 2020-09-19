package org.example.jdbc.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksManager {

    private static final Logger logger = LoggerFactory.getLogger(BooksManager.class);
    private final ConnectionFactory connectionFactory;

    public BooksManager() {
        connectionFactory = new ConnectionFactory("database.properties");
    }

    public static void main(String[] args) {
        //Pobieramy połączenie
        BooksManager manager = new BooksManager();
        manager.listNonReservedBooks();
    }

    private void listNonReservedBooks() {
        try(Connection connection = connectionFactory.getConnection();
            Statement statement = connection.createStatement()) {
            String query = "select * from books where reserved=0;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                logger.info("Book title = {}", title);
            }
        } catch (SQLException throwables) {
            logger.error("Couldn't connect to database");
        }
    }
}
