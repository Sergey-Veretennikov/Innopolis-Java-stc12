package ru.innopolis.hw20.repository.dao.mappers;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    private static final Logger LOGGER = Logger.getLogger(StudentMapper.class);

    private StudentMapper() {
    }

    public static PreparedStatement getPreparedStatementFromStudent(PreparedStatement preparedStatement,
                                                                    Student student) {
        try {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getGroup().getId());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setString(5, student.getContact());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return preparedStatement;
    }

    public static List<Student> getStudentFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        List<Student> studentList = new ArrayList<>();
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                studentList.add(new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("contact"),
                        new Group(resultSet.getInt(7), resultSet.getString(8))));
            }
        }
        return studentList;
    }

    /**
     * Метод удаления Student или Group в зависимости от параметра deleteIdStudentOrGroup
     *
     * @param id
     * @param connection
     * @param deleteIdStudentOrGroup
     * @return
     */
    public static boolean deleteStudentOrGroup(int id, Connection connection, String deleteIdStudentOrGroup) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteIdStudentOrGroup)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return true;
        }
        return false;
    }
}
