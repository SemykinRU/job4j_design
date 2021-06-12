package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int size = 0;

    public SimpleArray(int size) {
        this.array = (T[]) new Object[size];
    }

    public int getSize() {
        return size;
    }

    public void add(T model) {
        resizeArray();
        array[size++] = model;
    }

    public void resizeArray() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length + 10];
            System.arraycopy(array, 0, newArray, 0, size);
            array = (T[]) newArray;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, this.size);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, this.size);
        Object[] newArray = new Object[array.length];
        System.arraycopy(array, 0, newArray, 0, size);
        System.arraycopy(array, index + 1, newArray, index, size - 1);
        size--;
        array = (T[]) newArray;
    }

    public Object get(int index) {
        Objects.checkIndex(index, this.size);
        return  array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(array)
                .takeWhile(Objects::nonNull)
                .iterator();
    }
}
