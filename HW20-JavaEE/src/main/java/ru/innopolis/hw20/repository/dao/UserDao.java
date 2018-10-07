package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.User;

public interface UserDao {
    User getUserByLogin(String login);
}
