package ru.innopolis.hw04.objectBox;

import ru.innopolis.hw04.Exception.CheckTypeArrayElementsException;
import ru.innopolis.hw04.Exception.MyClassCastException;

import java.util.*;

public class ObjectBox {
    private final SortedSet<Object> objectSortedSet;

    public ObjectBox() {
        this.objectSortedSet = new TreeSet<>(Arrays.asList(0));
    }

    public ObjectBox(Object[] objectSet) {
        this.objectSortedSet = new TreeSet<>(Arrays.asList(checkTypeArrayElements(objectSet)));
    }

    private Object[] checkTypeArrayElements(Object[] objects) {
        for (int i = 1; i < objects.length; i++) {
            if (!(objects[0].getClass().isInstance(objects[i]))) {
                throw new CheckTypeArrayElementsException(objects[i]);
            }
        }
        return objects;
    }

    public void dump() {
        for (Object object : objectSortedSet) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    public boolean checkAvailabilityObject(Object object) {
        if (object.getClass().isInstance(objectSortedSet.first())) {
            if (objectSortedSet.contains(object)) {
                System.out.println("Элемент " + object + " присутствует в коллекции");
                return true;
            } else {
                System.out.println("Элемент " + object + " отсутствуе в коллекции");
                return false;
            }
        } else {
            System.out.println("Тип коллекции " + objectSortedSet.first().getClass().getName() + " не соответствует " +
                    "типу " + "искомого элемента " + object.getClass().getName());
            return false;
        }
    }

    public Object summator() {
        if (Number.class.isInstance(objectSortedSet.first())) {
            Double sum = 0.;
            for (Iterator<Object> iterator = objectSortedSet.iterator(); iterator.hasNext(); ) {
                Object object = iterator.next();
                sum = sum + ((Number) object).doubleValue();
            }
            return sum;
        } else {
            throw new MyClassCastException(objectSortedSet.first());
        }
    }

    public List<Double> splitter(Object divider) {
        List<Double> listResult = new ArrayList();

        if (!(divider instanceof Number) || ((Number) divider).doubleValue() != 0) {
            for (Iterator<Object> iterator = objectSortedSet.iterator(); iterator.hasNext(); ) {
                Object object = iterator.next();
                if (!(object instanceof Number)) {
                    throw new MyClassCastException(object);
                } else {
                    listResult.add(((Number) object).doubleValue() / ((Number) divider).doubleValue());
                }
            }
        } else {
            listResult.add(0.);
        }
        return listResult;
    }

    public boolean searchDeletingObject(Object object) {
        for (Iterator<Object> iterator = objectSortedSet.iterator(); iterator.hasNext(); ) {
            Object obj = iterator.next();
            if (obj.equals(object)) {
                iterator.remove();
                System.out.println("Элемент " + object + " удален из коллекции");
                return true;
            }
        }
        System.out.println("Элемент " + object + " не найден в коллекции");
        return false;
    }

    public boolean addObject(Object object) {
        if (object.getClass().isInstance(objectSortedSet.first())) {
            objectSortedSet.add(object);
            System.out.println("Элемент " + object + " добавлен в коллекцию");
            return true;
        } else {
            System.out.println("Тип коллекции " + objectSortedSet.first().getClass().getName() + " не соответствует" +
                    " типу " + "добовляемого элемента " + object.getClass().getName());
            return false;
        }
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "objectSortedSet=" + objectSortedSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox objectBox = (ObjectBox) o;
        return Objects.equals(objectSortedSet, objectBox.objectSortedSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectSortedSet);
    }
}
