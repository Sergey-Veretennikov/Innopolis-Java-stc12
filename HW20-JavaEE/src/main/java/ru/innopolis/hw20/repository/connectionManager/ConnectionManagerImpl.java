package ru.innopolis.hw20.repository.connectionManager;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManagerImpl.class);
    private static final String URL = "jdbc:postgresql:" +
            "//localhost:5432" +
            "/Innopolis University";
    private static ConnectionManager connectionManager;

    private ConnectionManagerImpl() {
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerImpl();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(URL, "postgres", "1");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }
}
