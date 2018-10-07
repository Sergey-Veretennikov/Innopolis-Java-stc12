package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.User;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManager;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManagerImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users WHERE users.username = ?");) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return new User(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
