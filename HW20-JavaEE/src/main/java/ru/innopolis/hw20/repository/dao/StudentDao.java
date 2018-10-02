package ru.innopolis.hw20.repository.dao;

import ru.innopolis.hw20.pojo.Student;

import java.util.List;

public interface StudentDao extends AutoCloseable {
    boolean addStudent(Student student);

    Student getStudentById(int id);

    boolean update(Student student);

    boolean deleteStudentById(int id);

    boolean deleteStudentByName(Student student);

    List<Student> getAllStudents();
}
