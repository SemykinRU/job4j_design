package ru.job4j.generics;

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
        Objects.checkIndex(index, point);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, point);
        System.arraycopy(array, index + 1, array, index, point - (index + 1));
        this.point--;
    }

    public Object get(int index) {
        Objects.checkIndex(index, point);
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(array, point);
    }
}
