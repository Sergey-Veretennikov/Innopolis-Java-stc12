package ru.innopolis.hw05;

import ru.innopolis.hw05.department.Department;
import ru.innopolis.hw05.department.DepartmentImpl;

/**
 * Есть клас Employee и есть класс DepartmentImpl который может работать с Employee
 * (сохранять, удалять и т.д.)
 * Дополнительно реализовал тестирование JUnit - DepartmentImplTest
 */
public class Main {

    public static void main(String[] args) {

        Department department = new DepartmentImpl();
        Employee employee1 = new Employee("Sergey", 40, 1000, Job.Junior);
        Employee employee2 = new Employee("Pavel", 30, 2000, Job.Middle);
        Employee employee3 = new Employee("Olga", 35, 3000, Job.Senior);
        Employee employee4 = new Employee("Igor", 40, 4000, Job.Junior);

        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);

        for (Employee empl : ((DepartmentImpl) department).getEmployeeList()) {
            System.out.println(empl.toString());
        }
        System.out.println();

        department.delete(employee4);
        System.out.println(department.getByName(employee4.getName()));
        System.out.println(department.getByJob(Job.Senior));
        employee2.setJob(Job.Junior);
        department.saveOrUpdate(employee2);
        department.changeAllWork(Job.Junior, Job.Team_leader);
        System.out.println();

        ((DepartmentImpl) department).deserializingMethod();
        for (Employee empl : ((DepartmentImpl) department).getEmployeeList()) {
            System.out.println(empl.toString());
        }

        System.out.println("sumSalary = " + Employee.getSumSalary());
    }
}
