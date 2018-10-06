package ru.innopolis.hw20.service;

import org.apache.log4j.Logger;
import ru.innopolis.hw20.pojo.Group;
import ru.innopolis.hw20.pojo.Student;
import ru.innopolis.hw20.repository.dao.StudentDao;
import ru.innopolis.hw20.repository.dao.StudentDaoImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = Logger.getLogger(StudentServiceImpl.class);
    private StudentDao studentDao;
    private List<Student> studentList;
    private Boolean flagStudentExists = false;

    public StudentServiceImpl() {
        studentDao = new StudentDaoImpl();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void AddStudents(String name, String surname, String nameGroup, String age, String contact) {
        Student student = null;
        studentList = studentDao.getStudentBySurname(surname);
        for (Student st : studentList) {
            if (st.getName().equals(name) && st.getSurname().equals(surname) &&
                    st.getGroup().getName().equals(nameGroup) && st.getAge() == Integer.parseInt(age) &&
                    st.getContact().equals(contact)) {
                student = st;
                flagStudentExists = true;
            }
        }
        if (student == null) {
            student = new Student(0, name, surname, Integer.parseInt(age), contact, new Group(0, nameGroup));
            studentDao.addStudent(student);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(student);
            }
        }
    }

    public Boolean getFlagStudentExists() {
        return flagStudentExists;
    }

    public void setFlagStudentExists(Boolean flagStudentExists) {
        this.flagStudentExists = flagStudentExists;
    }
}
