package ru.innopolis.hw04;

import ru.innopolis.hw04.Exception.CheckTypeArrayElementsException;
import ru.innopolis.hw04.Exception.MyClassCastException;
import ru.innopolis.hw04.mathBox.MathBox;
import ru.innopolis.hw04.objectBox.ObjectBox;

public class Main {
    public static void main(String[] args) {
        Object[] arrayObject = {5., 4.5, 5.8, 8., 7., 10.};
        Object[] arrayObject2 = {"as", "sd", "dc", "vf", "fd"};
        Object[] arrayObject3 = {5, 4, 5, 8, 9, 10};
        Object[] arrayObject4 = {'a', 's', 'f', 'g', 'j'};

        try {
            ObjectBox objectBox = new ObjectBox(arrayObject);
            objectBox.dump();
            objectBox.checkAvailabilityObject('a');
            System.out.println(objectBox.summator());
            System.out.println(objectBox.splitter(3));
            objectBox.searchDeletingObject("8.");
            objectBox.addObject("li");
            objectBox.dump();
        } catch (MyClassCastException | CheckTypeArrayElementsException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------");

        Number[] arrayNumber = {5., 4.5, 5.8, 8., 9., 10.};
        Number[] arrayNumber2 = {5, 4, 5, 8, 9, 10};

        try {
            MathBox mathBox = new MathBox(arrayNumber);

            mathBox.dump();
            mathBox.checkAvailabilityNumber(10);
            System.out.println(mathBox.summator());
            System.out.println(mathBox.splitterNumber(11.));
            System.out.println(mathBox.searchDeletingNumber(8.));
            System.out.println(mathBox.addONumber(11.));
            mathBox.dump();
            System.out.println(mathBox.toString());
        } catch (CheckTypeArrayElementsException e) {
            e.printStackTrace();
        }
    }
}
