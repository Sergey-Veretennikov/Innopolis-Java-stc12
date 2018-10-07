package ru.innopolis.hw20.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.User;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManager;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?)";
    private static final ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    Connection connection;

    public UserDaoImpl() {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public User getUserByLogin(String login) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE users.username = ?");) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4));
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRole());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
