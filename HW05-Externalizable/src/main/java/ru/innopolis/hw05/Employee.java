package ru.innopolis.hw05;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
import java.util.UUID;

public class Employee implements Externalizable {
    static int sumSalary = 0;
    private String id = UUID.randomUUID().toString();
    private String name;
    private int age;
    private int salary;
    private Job job;

    public Employee() {
    }

    public Employee(String name, int age, int salary, Job job) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.job = job;
    }

    public static int getSumSalary() {
        return sumSalary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", job=" + job +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                salary == employee.salary &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                job == employee.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, job);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(age);
        out.writeObject(salary);
        out.writeObject(job);
        sumSalary = 0;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id = (String) in.readObject();
        name = (String) in.readObject();
        age = (int) in.readObject();
        salary = (int) in.readObject();
        job = (Job) in.readObject();
        sumSalary = sumSalary + salary;
    }

}
