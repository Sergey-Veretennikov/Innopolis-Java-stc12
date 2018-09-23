package ru.innopolis.hw15;

import ru.innopolis.hw15.pojo.Group;
import ru.innopolis.hw15.pojo.Student;

public class Main {
    public static void main(String[] args) {

        Group group = new Group(0, "sct-15");
        Group group1 = new Group(0, "sct-16");
        Group group2 = new Group(0, "sct-17");
 /*       try (GroupDao groupDao = new GroupDaoImpl()) {
            groupDao.addGroup(group);
            group.setName("sct-10");
            group = groupDao.getGroupByName(group.getName());
            System.out.println(group);
            groupDao.update(group);
            System.out.println(groupDao.getGroupById(group.getId()));
            System.out.println(groupDao.deleteGroupByName(group));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Student student = new Student(0, "Sergey", "Ivanov", 20, "okmnji", group);
        Student student1 = new Student(0, "Egor", "Petrov", 25, "okmnji", group1);
        Student student2 = new Student(0, "Elena", "Sidorova", 25, "okmnji", group1);
/*        try (StudentDao studentDao = new StudentDaoImpl()) {
            studentDao.addStudent(student);
            student = studentDao.getStudentById(37);
            student.setName("Sergey");
            student.setSurname("Vasilev");
            System.out.println(studentDao.update(student));
            studentDao.deleteStudentById(37);
            studentDao.deleteStudentByName(studentDao.getStudentById(38));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
