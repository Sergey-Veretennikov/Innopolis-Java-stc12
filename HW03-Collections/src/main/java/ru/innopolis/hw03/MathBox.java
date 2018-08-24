package ru.innopolis.hw03;

import java.util.*;

public class MathBox {
    final private Set<Integer> set;

    public MathBox(Integer[] arr) {
        this.set = new TreeSet<>(Arrays.asList(arr));
    }

    public void print() {
        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    public Integer summator() {
        Integer sum = 0;
        for (Integer integer : set) {
            sum = sum + integer;
        }
        return sum;
    }

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

    public void searchDeletingNumber(Integer number) {

        for (Iterator<Integer> i = set.iterator(); i.hasNext(); ) {
            Integer element = i.next();
            if (element == number) {
                i.remove();
            }
        }
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
