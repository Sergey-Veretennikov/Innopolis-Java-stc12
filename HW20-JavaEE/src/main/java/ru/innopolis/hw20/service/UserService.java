package ru.innopolis.hw20.service;

public interface UserService {
    int getRole(String login);

    boolean checkAuth(String login, String password);
}
