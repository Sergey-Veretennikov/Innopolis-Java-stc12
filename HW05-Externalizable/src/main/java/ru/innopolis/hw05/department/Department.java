package ru.innopolis.hw05.department;

import ru.innopolis.hw05.Employee;
import ru.innopolis.hw05.Job;

import java.util.List;

public interface Department {
    boolean save(Employee employee);

    boolean delete(Employee employee);

    Employee getByName(String name);

    List<Employee> getByJob(Job job);

    boolean saveOrUpdate(Employee employee);

    boolean changeAllWork(Job deleteJob, Job addJob);
}
