package ru.innopolis.hw01;

public class Main {
    public static void main(String[] args) {

        System.out.println(new MathBox().summa(5, 1));
        System.out.println(new MathBox().diff(-1, 0));

        System.out.println(new MathBox().factorial(15));

        System.out.println(new MathBox().dividerExceptionInside(14, 5));

        try {
            new MathBox().divider(5, 0);
        } catch (MyArithmeticException e) {
            e.printStackTrace();
        }
    }
}
