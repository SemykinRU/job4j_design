package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] container;
    private final int lastIndexItem;
    private final int exceptedModCount;
    private int cursor = 0;

    public SimpleArrayIterator(T[] container, int lastIndexItem, int modCount) {
        this.container = container;
        this.lastIndexItem = lastIndexItem;
        this.exceptedModCount = modCount;
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
        if (exceptedModCount != SimpleArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return container[cursor++];
    }
}
