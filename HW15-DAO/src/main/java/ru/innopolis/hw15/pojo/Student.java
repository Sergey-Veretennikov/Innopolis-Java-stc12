package ru.innopolis.hw15.pojo;

public class Student {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String contact;
    private Group group;

    public Student(int id, String name, String surname, int age, String contact, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.contact = contact;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", group=" + group +
                '}';
    }
}
