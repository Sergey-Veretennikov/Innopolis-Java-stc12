package ru.innopolis.hw20.service;

import ru.innopolis.hw20.pojo.User;

public interface UserService {
    int getRole(String login);

    User checkAuth(String login, String password);

    boolean addUser(String login, String password, int role);
}
