package ru.innopolis.hw07;

import ru.innopolis.hw03.HW07.MathBoxInter;
import ru.innopolis.hw03.MathBox;

import java.lang.reflect.Proxy;

/**
 * Интерфейс для MathBox и аннотации создал в уроке 3 (HW03-Collections).
 * Интерфейс, аннотации и класс MathBox импортировал для работы.
 * Для сериализации и десериализации использовал Gson (com.google.code.gson)
 */
public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 10, 10, 5, 15, 0, 0};

        MathBoxInter mathBox = new MathBox(arr);
        MathBoxProxy mathBoxProxy = new MathBoxProxy(mathBox);

        MathBoxInter mathBoxTwo = (MathBoxInter) Proxy.newProxyInstance(MathBoxProxy.class.getClassLoader(),
                new Class[]{MathBoxInter.class}, mathBoxProxy);

        mathBoxTwo.print();
        System.out.println(mathBoxTwo.splitter(10));
        mathBoxTwo.summator();
        mathBoxTwo.serializationMethod();
        mathBoxTwo.cleanSet();
        mathBoxTwo.deserializingMethod();
        mathBoxTwo.print();
    }
}
