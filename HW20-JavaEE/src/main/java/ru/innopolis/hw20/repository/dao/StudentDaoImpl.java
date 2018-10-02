package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManager;
import ru.innopolis.hw20.repository.connectionManager.ConnectionManagerImpl;
import ru.innopolis.hw20.repository.dao.mappers.StudentMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        Student student = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM studens INNER JOIN courses ON courses.id=studens.group_id");
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
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
            e.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed. Bye!");
    }
}
