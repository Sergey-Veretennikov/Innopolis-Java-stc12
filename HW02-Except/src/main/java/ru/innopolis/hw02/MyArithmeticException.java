package ru.innopolis.hw02;

public class MyArithmeticException extends Exception {

    public MyArithmeticException() {
        super("На ноль делить нельзя!!!");
    }
}
