package ru.innopolis.hw05.department;

import ru.innopolis.hw05.Employee;
import ru.innopolis.hw05.Job;
import ru.innopolis.hw05.NoSuchEmployeeException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentImpl implements Department {
    private final String FILENAME = "Employee.dat";
    private List<Employee> employeeList;

    public DepartmentImpl() {
        this.employeeList = new ArrayList<>();
        serializationMethod();
    }

    @Override
    public boolean save(Employee employee) {
        deserializingMethod();
        if (!employeeList.contains(employee)) {
            employeeList.add(employee);
        }
        serializationMethod();
        return true;
    }

    @Override
    public boolean delete(Employee employee) {
        deserializingMethod();
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            serializationMethod();
            return true;
        } else {
            try {
                throw new NoSuchEmployeeException(employee);
            } catch (NoSuchEmployeeException e) {
                e.printStackTrace();
            } finally {
                serializationMethod();
            }
        }
        return false;
    }

    @Override
    public Employee getByName(String name) {
        deserializingMethod();
        for (Employee empl : employeeList) {
            if (empl.getName().equals(name)) {
                serializationMethod();
                return empl;
            }
        }
        serializationMethod();
        System.out.println(name + " Не работает в отделе");
        return null;
    }

    @Override
    public List<Employee> getByJob(Job job) {
        List<Employee> employees = new ArrayList<>();
        deserializingMethod();
        for (Employee empl : employeeList) {
            if (empl.getJob() == job) {
                employees.add(empl);
            }
        }
        serializationMethod();
        return employees;
    }

    /**
     * Можно использовать методы:
     * public boolean save(Employee employee) и public boolean delete(Employee employee)
     * но данная реализация использует меньше ресурсов.
     *
     * @param employee
     * @return
     */
    @Override
    public boolean saveOrUpdate(Employee employee) {
        deserializingMethod();
        for (Employee empl : employeeList) {
            if (empl.getId().equals(employee.getId())) {
                employeeList.set(employeeList.indexOf(empl), employee);
                serializationMethod();
                return true;
            }
        }
        save(employee);
        return true;
    }

    @Override
    public boolean changeAllWork(Job deleteJob, Job addJob) {
        deserializingMethod();
        boolean flag = false;
        for (Employee empl : employeeList) {
            if (empl.getJob() == deleteJob) {
                int index = employeeList.indexOf(empl);
                empl.setJob(addJob);
                employeeList.set(index, empl);
                flag = true;
            }
        }
        serializationMethod();
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    private void serializationMethod() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(employeeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserializingMethod() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            employeeList = (List<Employee>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

}
