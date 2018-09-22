package ru.innopolis.hw15;

import ru.innopolis.hw15.dao.StudentDao;
import ru.innopolis.hw15.dao.StudentDaoImpl;
import ru.innopolis.hw15.pojo.Group;
import ru.innopolis.hw15.pojo.Student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(0, "Sergey", "Ivanova", 20, "okmnji",
                new Group(0, "sct-15"));
        try (StudentDao studentDao = new StudentDaoImpl()) {
//            studentDao.addStudent(student);
            student = studentDao.getStudentById(34);
//            System.out.println(student);
//            student.setName("Pasha");
//            System.out.println(student);
//            System.out.println(studentDao.update(student));
            studentDao.deleteStudentByName(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        System.out.println(studentDao.getStudentById(30));
//        try (GroupDao groupDao = new GroupDaoImpl()) {
//            System.out.println(groupDao.addGroup(new Group(0, "qazx")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        System.out.println(groupDao.deleteGroupByName(new Group(0,"qazxsw")));
//        System.out.println(groupDao.getGroupByName("stc-1"));
//        System.out.println(student);
//        studentDao.getStudent(3);

    }
}
