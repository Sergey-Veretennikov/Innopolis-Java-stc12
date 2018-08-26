package ru.innopolis.hw04.Exception;

public class CheckTypeArrayElementsException extends ClassCastException {
    public CheckTypeArrayElementsException(Object object) {
        super("Тип элемента " + object + " отличается от остального типа элементов массива");
    }
}
