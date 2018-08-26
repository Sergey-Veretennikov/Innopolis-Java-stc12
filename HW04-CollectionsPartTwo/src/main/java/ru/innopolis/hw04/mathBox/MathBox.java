package ru.innopolis.hw04.mathBox;

import ru.innopolis.hw04.Exception.CheckTypeArrayElementsException;
import ru.innopolis.hw04.objectBox.ObjectBox;

import java.util.*;

public class MathBox extends ObjectBox {
    private final SortedSet<Number> numbersSortedSet;

    public MathBox(Number[] numbers) {
        this.numbersSortedSet = new TreeSet<>(Arrays.asList(checkTypeArrayElements(numbers)));
    }

    private Number[] checkTypeArrayElements(Number[] number) {
        for (int i = 1; i < number.length; i++) {
            if (!(number[0].getClass().isInstance(number[i]))) {
                throw new CheckTypeArrayElementsException(number[i]);
            }
        }
        return number;
    }

    @Override
    public void dump() {
        for (Object object : numbersSortedSet) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public boolean checkAvailabilityNumber(Number number) {
        if (number.getClass().isInstance(numbersSortedSet.first())) {
            if (numbersSortedSet.contains(number)) {
                System.out.println("Элемент " + number + " присутствует в коллекции");
                return true;
            } else {
                System.out.println("Элемент " + number + " отсутствуе в коллекции");
                return false;
            }
        } else {
            System.out.println("Тип коллекции " + numbersSortedSet.first().getClass().getName() + " не соответствует " +
                    "типу " + "искомого элемента " + number.getClass().getName());
            return false;
        }
    }

    @Override
    public Object summator() {
        Double sum = 0.;
        for (Iterator<Number> iterator = numbersSortedSet.iterator(); iterator.hasNext(); ) {
            Number number = iterator.next();
            sum = sum + number.doubleValue();
        }
        return sum;
    }

    public List<Double> splitter(Number divider) {
        List<Double> listResult = new ArrayList();

        if (divider.doubleValue() != 0) {
            for (Iterator<Number> iterator = numbersSortedSet.iterator(); iterator.hasNext(); ) {
                Number number = iterator.next();
                listResult.add(number.doubleValue() / divider.doubleValue());
            }
        } else {
            listResult.add(0.);
        }
        return listResult;
    }

    public boolean searchDeletingNumber(Number number) {
        for (Iterator<Number> iterator = numbersSortedSet.iterator(); iterator.hasNext(); ) {
            Number num = iterator.next();
            if (num.equals(number)) {
                iterator.remove();
                System.out.println("Элемент " + number + " удален из коллекции");
                return true;
            }
        }
        System.out.println("Элемент " + number + " не найден в коллекции");
        return false;
    }

    public boolean addONumber(Number number) {
        if (number.getClass().isInstance(numbersSortedSet.first())) {
            numbersSortedSet.add(number);
            System.out.println("Элемент " + number + " добавлен в коллекцию");
            return true;
        } else {
            System.out.println("Тип коллекции " + numbersSortedSet.first().getClass().getName() + " не соответствует" +
                    " типу " + "добовляемого элемента " + number.getClass().getName());
            return false;
        }
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "numbersSortedSet=" + numbersSortedSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(numbersSortedSet, mathBox.numbersSortedSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numbersSortedSet);
    }
}
