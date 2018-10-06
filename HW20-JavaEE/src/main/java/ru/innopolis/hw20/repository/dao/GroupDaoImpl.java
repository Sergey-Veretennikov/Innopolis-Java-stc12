package ru.innopolis.hw20.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManager;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManagerImpl;
import ru.innopolis.hw20.repository.dao.mappers.StudentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDaoImpl implements GroupDao {
    private static final Logger LOGGER = Logger.getLogger(GroupDaoImpl.class);
    private static final String INSERT_COURSE = "INSERT INTO courses VALUES (DEFAULT, ?)";
    private static final String SELECT_NAME_COURSE = "SELECT * FROM courses WHERE name = ?";
    private static final String SELECT_ID_COURSE = "SELECT * FROM courses WHERE id = ?";
    private static final String UPDATE_COURSE = "UPDATE courses SET name = ? WHERE id = ?";
    private static final String DELETE_ID_COURSE = "DELETE FROM courses WHERE id = ?";
    private static final String DELETE_NAME_COURSE = "DELETE FROM courses WHERE name = ?";
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private final Connection connection;

    public GroupDaoImpl() {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public boolean addGroup(Group group) {
        if (addOrDeleteGroup(group, connection, INSERT_COURSE)) {
            return false;
        }
        return true;
    }

    @Override
    public Group getGroupByName(String groupName) {
        Group group;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_COURSE)) {
            preparedStatement.setString(1, groupName);
            group = getGroup(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return group;
    }

    @Override
    public Group getGroupById(int id) {
        Group group;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_COURSE)) {
            preparedStatement.setInt(1, id);
            group = getGroup(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return group;
    }

    @Override
    public boolean update(Group group) {
        if (group.getId() != 0) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE)) {
                preparedStatement.setString(1, group.getName());
                preparedStatement.setInt(2, group.getId());
                preparedStatement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteGroupById(int id) {
        if (getGroupById(id) != null) {
            if (StudentMapper.deleteStudentOrGroup(id, connection, DELETE_ID_COURSE)) return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteGroupByName(Group group) {
        if (getGroupByName(group.getName()) != null) {
            if (addOrDeleteGroup(group, connection, DELETE_NAME_COURSE)) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        LOGGER.info(connection + "Connection closed");
    }

    /**
     * Метод добавления или удаление группы, в зависимости параметра addOrdeleteNameCourse
     *
     * @param group
     * @param connection
     * @param addOrdeleteNameCourse
     * @return
     */
    private boolean addOrDeleteGroup(Group group, Connection connection, String addOrdeleteNameCourse) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(addOrdeleteNameCourse)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return true;
        }
        return false;
    }

    private Group getGroup(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return new Group(
                        resultSet.getInt("id"),
                        resultSet.getString("name"));
            } else {
                return null;
            }
        }
    }
}
