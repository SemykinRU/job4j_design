package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int point = 0;

    public SimpleArray(int size) {
        this.array = (T[]) new Object[size];
    }

    public void add(T model) {
        Objects.checkIndex(point, array.length);
        array[point++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, array.length);
        if (index > this.point) {
            index = this.point;
            this.point++;
        }
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, array.length);
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        System.arraycopy(array, index + 1, newArray, index, array.length - (index + 1));
        this.point--;
        array = (T[]) newArray;
    }

    public Object get(int index) {
        Objects.checkIndex(index, array.length);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array)
                .takeWhile(Objects::nonNull)
                .iterator();
    }
}
