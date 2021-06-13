package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] container;
    private int point = 0;
    private static int modCount = 0;

    public SimpleArray() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArray(int value) {
        this.container = (T[]) new Object[value];
    }

    public static int getModCount() {
        return modCount;
    }

    public int getLength() {
        return container.length;
    }

    public T get(int index) {
        Objects.checkIndex(index, point);
        return (T) container[index];
    }

    public void add(T model) {
        if (point == container.length) {
            Object[] newContainer = new Object[container.length + 10];
            System.arraycopy(container, 0, newContainer, 0, point);
            container = (T[]) newContainer;
        }
        container[point++] = model;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<>(container, point, modCount);
    }
}