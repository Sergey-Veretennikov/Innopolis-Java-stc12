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
    }

    public Integer summator() {
        Integer sum = 0;
        for (Integer integer : set) {
            sum = sum + integer;
        }
        return sum;
    }

    public List splitter(Integer divider) {
        List<Double> listResult = new ArrayList();
        for (Integer integer : set) {
            listResult.add((double) integer / divider);
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

}
