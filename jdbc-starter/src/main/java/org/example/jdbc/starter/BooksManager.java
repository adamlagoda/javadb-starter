package org.example.jdbc.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BooksManager {

    private static final Logger logger = LoggerFactory.getLogger(BooksManager.class);
    private final ConnectionFactory connectionFactory;

    public BooksManager() {
        connectionFactory = new ConnectionFactory("database.properties");
    }

    public static void main(String[] args) {
        //Pobieramy połączenie
        BooksManager manager = new BooksManager();
        //manager.listNonReservedBooks();
        List<String> booksWithCategories = manager.findBooksWithCategories();
        for (String book : booksWithCategories) {
            logger.info(book);
        }
    }

    public List<String> listNonReservedBooks() {
        List<String> result = new ArrayList<>();
        try(Connection connection = connectionFactory.getConnection();
            Statement statement = connection.createStatement()) {
            String query = "select * from books where reserved=0;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                logger.info("Book title = {}", title);
                result.add(title);
            }
        } catch (SQLException throwables) {
            logger.error("Couldn't connect to database");
        }
        return result;
    }

    public boolean addBook(String title) {
        String sql = "insert into books (title, publish_date, ISBN, pages, publisher," +
                "netto_price, vat_rate, reserved) values ('" + title +"'," +
                "'2020-09-20', '978-83-246-8898-8', 253, 'Helion', 34.54, 19, 0);";
        try(Connection connection = connectionFactory.getConnection();
        Statement insertBook = connection.createStatement()) {
            int i = insertBook.executeUpdate(sql);
            return i > 0;
        } catch (SQLException throwables) {
            logger.error("Couldn't add book");
        }
        return false;
    }

    public List<String> findBooksWithCategories() {
        List<String> booksWithCategories = new ArrayList<>();
        String sql = "Select b.book_id, b.title, c.name from books b left join books_categories bc " +
                "on b.book_id = bc.book_id left join categories c on c.category_id = bc.category_id;";
        try(Connection connection = connectionFactory.getConnection();
            Statement selectWithCategories = connection.createStatement()) {
            ResultSet resultSet = selectWithCategories.executeQuery(sql);
            while (resultSet.next()) {
                int bookId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String categoryName = resultSet.getString(3);
                String record = String.format("%d, %s, %s", bookId, name, categoryName);
                booksWithCategories.add(record);
            }
        } catch (SQLException throwables) {
            logger.error("Couldn't select books");
        }
        return booksWithCategories;
    }
}
