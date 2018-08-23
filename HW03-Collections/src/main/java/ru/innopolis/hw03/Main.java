package ru.innopolis.hw03;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 10, 10, 5, 15, 0, 0};

        MathBox mathBox = new MathBox(arr);
        mathBox.print();
        System.out.println(mathBox.summator());
        System.out.println(mathBox.splitter(0));
        mathBox.searchDeletingNumber(10);
        mathBox.print();
        System.out.println(mathBox.toString());

        System.out.println("----------------------------------------------------------------");

        MathBoxTwo mathBoxTwo = new MathBoxTwo(arr);
        mathBoxTwo.print();
        System.out.println(mathBoxTwo.summator());
        System.out.println(mathBoxTwo.splitter(9));
        mathBoxTwo.searchDeletingNumber(0);
        mathBoxTwo.print();
        System.out.println(mathBoxTwo.toString());
    }
}
