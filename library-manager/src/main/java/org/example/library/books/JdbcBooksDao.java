package org.example.library.books;

import org.example.jdbc.starter.ConnectionFactory;
import org.example.library.users.JdbcUsersDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JdbcBooksDao implements IBooksDao {

    private static final String DATABASE_PROPERTIES = "library-database.properties";
    private static final Logger logger = LoggerFactory.getLogger(JdbcBooksDao.class);
    private ConnectionFactory connectionFactory;

    public JdbcBooksDao() {
        connectionFactory = new ConnectionFactory(DATABASE_PROPERTIES);
    }

    @Override
    public List<Book> list() {
        return new ArrayList<>();
    }

    @Override
    public void add(Book book) {
    }

    @Override
    public void delete(int bookId) {
    }

    @Override
    public List<Category> listCategories() {
        return new ArrayList<>();
    }

    @Override
    public List<Book> listAllMatchingPhraseTitle(String phrase) {
        List<Book> books = new LinkedList<>();
        logger.info("Szukam książki, której tytuł zawiera frazę {}", phrase);
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement selectMatchingPhrase = connection.prepareStatement("SELECT * FROM books b " +
                     "LEFT JOIN categories c ON b.category_id = c.id WHERE b.title LIKE ?")) {
            selectMatchingPhrase.setString(1, "%" + phrase + "%");
            ResultSet booksSet = selectMatchingPhrase.executeQuery();
            while (booksSet.next()) {
                int id = booksSet.getInt("b.id");
                String title = booksSet.getString("b.title");
                int categoryId = booksSet.getInt("b.category_id");
                String author = booksSet.getString("b.author");
                String categoryName = booksSet.getString("c.name");
                Book book = new Book(id, title, author, categoryId, categoryName);
                books.add(book);
            }
        } catch (SQLException throwables) {
            logger.error(throwables.getSQLState());
        }
        return books;
    }
}