package ru.innopolis.hw20.service;

import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.dao.StudentDao;
import ru.innopolis.hw20.repository.dao.StudentDaoImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void AddStudents(String name, String surname, String nameGroup, String age, String contact) {
        Student student = new Student(0, name, surname, Integer.parseInt(age), contact, new Group(0, nameGroup));
        studentDao.addStudent(student);
    }
}
