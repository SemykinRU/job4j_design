package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private int cursor = 0;
    private final T[] array;
    private final int lastIndexItem;

    public SimpleArrayIterator(T[] array, int point) {
        this.array = array;
        this.lastIndexItem = point;
    }


    @Override
    public boolean hasNext() {
        return cursor < lastIndexItem;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[cursor++];
    }
}
