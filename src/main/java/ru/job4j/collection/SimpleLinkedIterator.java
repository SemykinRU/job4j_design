package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedIterator<T> implements Iterator<T> {
    private SimpleLinkedList<T> simpleLinkedList;
    private final int size;
    private final int exceptedModCount;
    private int cursor = 0;
    private Node<T> rsl;

    public SimpleLinkedIterator(SimpleLinkedList<T> simpleLinkedList) {
        this.simpleLinkedList = simpleLinkedList;
        this.size = simpleLinkedList.getSize();
        this.exceptedModCount = simpleLinkedList.getModCount();
        rsl = simpleLinkedList.getFirstNode();
    }

    @Override
    public boolean hasNext() {
        return cursor < size && exceptedModCount != 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (exceptedModCount != simpleLinkedList.getModCount()) {
            throw new ConcurrentModificationException();
        }
        rsl = rsl.getNextElement();
        cursor++;
        return rsl.getCurrentElement();
    }
}
