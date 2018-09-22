package ru.innopolis.hw15.dao;

import ru.innopolis.hw15.connectionManager.ConnectionManager;
import ru.innopolis.hw15.connectionManager.ConnectionManagerImpl;
import ru.innopolis.hw15.dao.mappers.StudentMapper;
import ru.innopolis.hw15.pojo.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDaoImpl implements GroupDao {
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
        if (addDeleteGroup(group, connection, INSERT_COURSE)) {
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
            e.printStackTrace();
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
            e.printStackTrace();
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
                e.printStackTrace();
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
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteGroupByName(Group group) {
        if (getGroupByName(group.getName()) != null) {
            if (addDeleteGroup(group, connection, DELETE_NAME_COURSE)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed. Bye!");
    }

    private boolean addDeleteGroup(Group group, Connection connection, String deleteNameCourse) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteNameCourse)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
