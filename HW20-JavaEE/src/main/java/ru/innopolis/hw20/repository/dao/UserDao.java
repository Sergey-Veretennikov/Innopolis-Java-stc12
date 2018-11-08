package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.User;

public interface UserDao extends AutoCloseable {
    User getUserByLogin(String login);

    boolean addUser(User user);
}
