package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final T[] container;
    private final int lastIndexItem;
    private final int exceptedModCount;
    private int cursor = 0;
    private final SimpleArray<T> simpleArray;

    public SimpleArrayIterator(SimpleArray<T> ts) {
       this.simpleArray = ts;
       this.container = ts.getContainer();
       this.lastIndexItem = ts.getLength();
       this.exceptedModCount = ts.getModCount();
    }

    @Override
    public boolean hasNext() {
        return cursor < lastIndexItem && exceptedModCount != 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (exceptedModCount != simpleArray.getModCount()) {
            throw new ConcurrentModificationException();
        }
        return container[cursor++];
    }
}
