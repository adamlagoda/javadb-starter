package org.example.library.users;

import org.example.jdbc.starter.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUsersDao implements IUsersDao {

    private static final String DATABASE_PROPERTIES = "library-database.properties";
    private static final Logger logger = LoggerFactory.getLogger(JdbcUsersDao.class);
    private ConnectionFactory connectionFactory;

    public JdbcUsersDao() {
        connectionFactory = new ConnectionFactory(DATABASE_PROPERTIES);
    }

    @Override
    public User findUser(String login, String password) {
        logger.info("Finding user by login = " + login + " and password = " + password);
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet userData = statement.executeQuery();
            if (userData.next()) {
                return new User(userData.getInt("id"), userData.getString("login"), userData.getString("password"),
                        userData.getString("name"), userData.getBoolean("admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> list() {
        return new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
    }

    @Override
    public void deleteUser(int userId) {
    }

    @Override
    public void updateUser(User toUpdate) {

    }
}