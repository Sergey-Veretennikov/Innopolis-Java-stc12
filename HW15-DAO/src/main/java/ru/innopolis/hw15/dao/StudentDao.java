package ru.innopolis.hw15.dao;

import ru.innopolis.hw15.pojo.Student;

public interface StudentDao extends AutoCloseable {
    boolean addStudent(Student student);

    Student getStudentById(int id);

    boolean update(Student student);

    boolean deleteStudentById(int id);

    boolean deleteStudentByName(Student student);
}
