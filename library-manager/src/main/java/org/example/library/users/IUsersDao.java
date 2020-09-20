package org.example.library.users;

import java.util.List;

public interface IUsersDao {
    User findUser(String login, String password);
    List<User> list();

    void addUser(User user);
    void deleteUser(int userId);
    void updateUser(User toUpdate);
}
