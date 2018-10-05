package ru.innopolis.hw20.service;

import ru.innopolis.hw20.pojo.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    void AddStudents(String name, String surname, String nameGroup, String age, String contact);
}
