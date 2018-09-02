package ru.innopolis.hw03;

import ru.innopolis.hw03.HW07.*;

import java.util.*;

public class MathBox implements MathBoxInter {
    final private Set<Integer> set;

    public MathBox(Integer[] arr) {
        this.set = new TreeSet<>(Arrays.asList(arr));
    }

    @Override
    public void print() {
        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    @Logged
    @Override
    public Integer summator() {
        Integer sum = 0;
        for (Integer integer : set) {
            sum += integer;
        }
        return sum;
    }

    /**
     * При divider = 0 метод возвращает List который хранит только 0
     */
    @Override
    public List<Double> splitter(Integer divider) {
        List<Double> listResult = new ArrayList();
        if (divider != 0) {
            for (Integer integer : set) {
                listResult.add((double) integer / divider);
            }
        } else {
            listResult.add(0.);
        }
        return listResult;
    }

    @Override
    public void searchDeletingNumber(Integer number) {
        set.remove(number);
    }

    @ClearData
    @Override
    public void cleanSet() {
    }

    @UseXmlSerialization
    @Override
    public void serializationMethod() {
    }

    @UseXmlDeserializingMethod
    @Override
    public void deserializingMethod() {
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "set=" + set +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(set, mathBox.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }
}
