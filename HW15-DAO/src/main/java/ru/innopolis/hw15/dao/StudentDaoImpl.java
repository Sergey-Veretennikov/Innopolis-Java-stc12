package ru.innopolis.hw15.dao;

import ru.innopolis.hw15.connectionManager.ConnectionManager;
import ru.innopolis.hw15.connectionManager.ConnectionManagerImpl;
import ru.innopolis.hw15.dao.mappers.StudentMapper;
import ru.innopolis.hw15.pojo.Group;
import ru.innopolis.hw15.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDaoImpl implements StudentDao {
    private static final String INSERT_STUDENTS = "INSERT INTO studens VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SELECT_ID_STUDENTS = "SELECT * FROM studens " +
            "INNER JOIN courses ON courses.id=studens.group_id WHERE studens.id=?";
    private static final String UPDATE_STUDENTS = "UPDATE studens " +
            "SET name = ?, surname = ?, group_id = ?, age = ?, contact = ? WHERE id = ?";
    private static final String DELETE_ID_STUDENTS = "DELETE FROM studens WHERE id = ?";
    private static final String DELETE_STUDENTS = "DELETE FROM studens WHERE name = ?";
    private static ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();
    private final Connection connection;

    public StudentDaoImpl() {
        this.connection = connectionManager.getConnection();
    }

    @Override
    public boolean addStudent(Student student) {
        try (GroupDao groupDao = new GroupDaoImpl()) {
            if (groupDao.getGroupByName(student.getGroup().getName()) == null) {
                groupDao.addGroup(new Group(0, student.getGroup().getName()));
            }
            student.setGroup(groupDao.getGroupByName(student.getGroup().getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS)) {
            StudentMapper.getPreparedStatementFromStudent(preparedStatement, student).execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Student getStudentById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_STUDENTS)) {
            preparedStatement.setInt(1, id);
            return StudentMapper.getStudentFromResultSet(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Student student) {
        if (student.getId() != 0) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS)) {
                StudentMapper.getPreparedStatementFromStudent(preparedStatement, student)
                        .setInt(6, student.getId());
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
    public boolean deleteStudentById(int id) {
        if (getStudentById(id) != null) {
            if (StudentMapper.deleteStudentOrGroup(id, connection, DELETE_ID_STUDENTS)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean deleteStudentByName(Student student) {
        if (getStudentById(student.getId()) != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
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
}
