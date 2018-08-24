package ru.innopolis.hw03;

import java.util.*;

public class MathBoxTwo {
    final private List<Integer> integerList;

    public MathBoxTwo(Integer[] array) {
        this.integerList = removeDuplicateNumbers((ArrayList<Integer>) insertSort(array));
    }

    public void print() {
        for (Integer integer : integerList) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    private List<Integer> removeDuplicateNumbers(ArrayList<Integer> arrayList) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!list.contains(arrayList.get(i))) {
                list.add(arrayList.get(i));
            }
        }
        return list;
    }

    private List<Integer> insertSort(Integer[] sourceArray) {
        Integer[] destinationArray = new Integer[sourceArray.length];
        int destinationSize = 0;
        for (int n = 0; n < sourceArray.length; n++) {
            int insertIndex = 0;
            if (destinationSize > 0) {
                while (insertIndex < destinationSize
                        && destinationArray[insertIndex] < sourceArray[n]) {
                    insertIndex++;
                }
            }
            for (int m = destinationSize - 1; m >= insertIndex; m--) {
                destinationArray[m + 1] = destinationArray[m];
            }
            destinationArray[insertIndex] = sourceArray[n];
            destinationSize++;
        }
        return new ArrayList<>(Arrays.asList(destinationArray));
    }

    public Integer summator() {
        Integer sum = 0;
        for (Integer integer : integerList) {
            sum = sum + integer;
        }
        return sum;
    }

    public List splitter(Integer divider) {
        List<Double> listResult = new ArrayList();
        if (divider != 0) {
            for (Integer integer : integerList) {
                listResult.add((double) integer / divider);
            }
        } else {
            listResult.add(0.);
        }
        return listResult;
    }

    public void searchDeletingNumber(Integer number) {

        for (Iterator<Integer> i = integerList.iterator(); i.hasNext(); ) {
            Integer element = i.next();
            if (element == number) {
                i.remove();
            }
        }
    }

    @Override
    public String toString() {
        return "MathBoxTwo{" +
                "integerList=" + integerList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBoxTwo that = (MathBoxTwo) o;
        return Objects.equals(integerList, that.integerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integerList);
    }

}
