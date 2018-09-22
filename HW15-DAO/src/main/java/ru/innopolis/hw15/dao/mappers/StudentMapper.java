package ru.innopolis.hw15.dao.mappers;

import ru.innopolis.hw15.pojo.Group;
import ru.innopolis.hw15.pojo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper {

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
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static Student getStudentFromResultSet(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getString("contact"),
                        new Group(resultSet.getInt(7), resultSet.getString(8)));
            } else {
                return null;
            }
        }
    }

    public static boolean deleteStudentOrGroup(int id, Connection connection, String deleteIdStudentOrGroup) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteIdStudentOrGroup)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
