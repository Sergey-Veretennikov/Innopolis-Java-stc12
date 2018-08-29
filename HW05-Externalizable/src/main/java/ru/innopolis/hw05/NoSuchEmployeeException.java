package ru.innopolis.hw05;

public class NoSuchEmployeeException extends Exception {
    public NoSuchEmployeeException(Employee employee) {
        super(employee + "Не работает в отделе");
    }

}
