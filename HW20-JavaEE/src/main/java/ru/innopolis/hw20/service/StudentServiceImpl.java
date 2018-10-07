package ru.innopolis.hw20.service;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.dao.StudentDao;
import ru.innopolis.hw20.repository.dao.StudentDaoImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);
    private final StudentDao studentDao;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public boolean addStudents(String name, String surname, String nameGroup, String age, String contact) {
        List<Student> studentList = studentDao.getStudentBySurname(surname);
        if (!studentList.isEmpty()) {
            for (Student st : studentList) {
                if (st.getName().equals(name) && st.getSurname().equals(surname) &&
                        st.getGroup().getName().equals(nameGroup) && st.getAge() == Integer.parseInt(age) &&
                        st.getContact().equals(contact)) {
                    return false;
                }
            }
        }
        Student student = new Student(0, name, surname, Integer.parseInt(age), contact,
                new Group(0, nameGroup));
        studentDao.addStudent(student);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(student);
        }
        return true;
    }
}
