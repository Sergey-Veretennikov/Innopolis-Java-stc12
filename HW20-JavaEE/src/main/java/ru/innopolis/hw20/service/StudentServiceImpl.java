package ru.innopolis.hw20.service;

import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.dao.StudentDao;
import ru.innopolis.hw20.repository.dao.StudentDaoImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }
}
