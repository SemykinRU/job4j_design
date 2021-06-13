package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private int cursor = 0;
    private T[] array;

    public SimpleArrayIterator(T[] array) {
        this.array = array;
    }


    @Override
    public boolean hasNext() {
        return cursor < array.length && array[cursor] != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[cursor++];
    }
}
