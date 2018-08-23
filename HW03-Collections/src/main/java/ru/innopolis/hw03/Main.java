package ru.innopolis.hw03;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 10, 10, 5, 15};
        MathBox mathBox = new MathBox(arr);
        mathBox.print();
        System.out.println("\n" + mathBox.summator());
        System.out.println(mathBox.splitter(5));
        mathBox.searchDeletingNumber(10);
        mathBox.print();

    }
}
