package ru.innopolis.hw01;

public class MyArithmeticException extends Exception {

    public MyArithmeticException() {
        super("На ноль делить нельзя!!!");
    }
}
