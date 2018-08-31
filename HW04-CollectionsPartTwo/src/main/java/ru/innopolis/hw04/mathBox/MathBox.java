package ru.innopolis.hw04.mathBox;

import ru.innopolis.hw04.objectBox.ObjectBox;

import java.util.List;

public class MathBox extends ObjectBox {

    public MathBox(Number[] numbers) {
        super(numbers);
    }

    public boolean checkAvailabilityNumber(Number number) {

        return super.checkAvailabilityObject(number);
    }

    public List<Double> splitterNumber(Number divider) {

        return super.splitter(divider);
    }

    public boolean searchDeletingNumber(Number number) {

        return super.searchDeletingObject(number);
    }

    public boolean addONumber(Number number) {

        return super.addObject(number);
    }

    @Override
    public String toString() {

        return "MathBox{" +
                "numberSortedSet=" + super.getObjectSortedSet() +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        return super.equals(o);
    }

    @Override
    public int hashCode() {

        return super.hashCode();
    }
}
