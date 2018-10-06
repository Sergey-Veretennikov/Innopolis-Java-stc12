package ru.innopolis.hw20.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManager;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManagerImpl;
import ru.innopolis.hw20.repository.dao.mappers.StudentMapper;
import ru.innopolis.hw20.service.StudentServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);
    private static final String INSERT_STUDENTS = "INSERT INTO studens VALUES (DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SELECT_ID_STUDENTS = "SELECT * FROM studens " +
            "INNER JOIN courses ON courses.id=studens.group_id WHERE studens.id=?";
    private static final String UPDATE_STUDENTS = "UPDATE studens " +
            "SET name = ?, surname = ?, group_id = ?, age = ?, contact = ? WHERE id = ?";
    private static final String DELETE_ID_STUDENTS = "DELETE FROM studens WHERE id = ?";
    private static final String DELETE_STUDENTS = "DELETE FROM studens WHERE name = ?";
    private static final String SELECT_STUDENTS = "SELECT * FROM studens INNER JOIN courses ON " +
            "courses.id=studens.group_id";
    private static final String SELECT_SURNAME_STUDENTS = "SELECT * FROM studens " +
            "INNER JOIN courses ON courses.id=studens.group_id WHERE studens.surname=?";
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
            LOGGER.error(e.getMessage(), e);
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS)) {
            StudentMapper.getPreparedStatementFromStudent(preparedStatement, student).execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    public Student getStudentById(int id) {
        List<Student> studentList;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_STUDENTS)) {
            preparedStatement.setInt(1, id);
            studentList = StudentMapper.getStudentFromResultSet(preparedStatement);
            if (studentList.isEmpty()) {
                return null;
            }
            return studentList.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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
                LOGGER.error(e.getMessage(), e);
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
        }
        return false;
    }

    @Override
    public boolean deleteStudentByName(Student student) {
        if (getStudentById(student.getId()) != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS)) {
                preparedStatement.setString(1, student.getName());
                preparedStatement.execute();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_STUDENTS)) {
                while (resultSet.next()) {
                    result.add(new Student(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getInt("age"),
                            resultSet.getString("contact"),
                            new Group(resultSet.getInt(7), resultSet.getString(8))));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Student> getStudentBySurname(String surname) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SURNAME_STUDENTS)) {
            preparedStatement.setString(1, surname);
            return StudentMapper.getStudentFromResultSet(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
        LOGGER.info("Connection closed" + connection);
    }
}
