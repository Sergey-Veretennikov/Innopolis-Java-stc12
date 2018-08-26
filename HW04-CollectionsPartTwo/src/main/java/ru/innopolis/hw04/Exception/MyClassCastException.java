package ru.innopolis.hw04.Exception;

public class MyClassCastException extends ClassCastException {
    public MyClassCastException(Object object) {
        super(object + " Не является наследником Number");
    }
}
