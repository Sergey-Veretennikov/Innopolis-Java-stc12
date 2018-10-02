package ru.innopolis.hw20.repository.connectionManager;

import java.sql.Connection;

public interface ConnectionManager {
    Connection getConnection();
}
