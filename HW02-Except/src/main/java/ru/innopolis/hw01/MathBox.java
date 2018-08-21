package ru.innopolis.hw01;

public class MathBox {

    public int summa(Integer a, Integer b) {

        return a + b;
    }

    public int diff(Integer a, Integer b) {

        return a - b;
    }

    public long factorial(Integer a) {

        return a * factorial(a - 1);
    }

    public double dividerExceptionInside(Integer a, Integer b) {

        if (b != 0) {
            return a / b;

        } else {
            System.out.println("На ноль делить нельзя!");
            return 0;
        }
    }

    public double divider(Integer a, Integer b) throws MyArithmeticException {

        if (b != 0) {
            return a / b;
        } else {
            throw new MyArithmeticException();
        }
    }
}
