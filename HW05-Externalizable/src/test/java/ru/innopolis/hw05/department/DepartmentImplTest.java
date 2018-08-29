package ru.innopolis.hw05.department;

import org.junit.Assert;
import org.junit.Test;
import ru.innopolis.hw05.Employee;
import ru.innopolis.hw05.Job;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImplTest {

    DepartmentImpl department = new DepartmentImpl();
    Employee employee1 = new Employee("Sergey", 40, 1000, Job.Junior);
    Employee employee2 = new Employee("Pavel", 30, 2000, Job.Middle);
    Employee employee3 = new Employee("Olga", 35, 3000, Job.Senior);
    Employee employee4 = new Employee("Igor", 40, 4000, Job.Team_leader);
    Employee employee5 = new Employee("Vera", 40, 4000, Job.Team_leader);

    @Test
    public void save() {
        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);

        Assert.assertEquals(employee1, department.getByName(employee1.getName()));
        Assert.assertEquals(employee2, department.getByName(employee2.getName()));
        Assert.assertEquals(employee3, department.getByName(employee3.getName()));
        Assert.assertEquals(employee4, department.getByName(employee4.getName()));
    }

    @Test
    public void delete() {
        department.save(employee1);
        department.save(employee2);
        department.delete(employee1);
        department.delete(employee2);

        Assert.assertNotEquals(employee1, department.getByName(employee1.getName()));
        Assert.assertNotEquals(employee2, department.getByName(employee2.getName()));
    }

    @Test
    public void getByName() {
        department.save(employee3);
        department.save(employee4);

        Assert.assertEquals(department.getByName(employee3.getName()), employee3);
        Assert.assertEquals(department.getByName(employee4.getName()), employee4);
    }

    @Test
    public void getByJob() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee4);
        employees.add(employee5);

        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);
        department.save(employee5);

        Assert.assertEquals(department.getByJob(Job.Team_leader), employees);
    }

    @Test
    public void saveOrUpdate() {
        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);
        employee1.setSalary(10000);
        department.saveOrUpdate(employee1);
        department.saveOrUpdate(employee5);

        Assert.assertEquals(department.getByName(employee1.getName()).getSalary(), employee1.getSalary());
        Assert.assertEquals(department.getByName(employee5.getName()), employee5);
    }

    @Test
    public void changeAllWork() {
        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);
        department.save(employee5);

        Assert.assertEquals(department.changeAllWork(Job.Team_leader, Job.Junior), true);
        Assert.assertEquals(department.getByName(employee4.getName()).getJob(), Job.Junior);
        Assert.assertEquals(department.getByName(employee5.getName()).getJob(), Job.Junior);
    }

    @Test
    public void getSumSalary() {
        department.save(employee1);
        department.save(employee2);
        department.save(employee3);
        department.save(employee4);
        department.save(employee5);
        int sum = 0;
        for (Employee eml : department.getEmployeeList()) {
            sum += eml.getSalary();
        }
        department.deserializingMethod();
        Assert.assertEquals(sum, Employee.getSumSalary());
    }
}