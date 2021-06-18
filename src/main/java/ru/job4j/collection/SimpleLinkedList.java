package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int modCount = 0;
    private int size = 0;

    public SimpleLinkedList() {
        this.lastNode = new Node<E>(null, firstNode, null);
        this.firstNode = new Node<E>(null, null, lastNode);
    }

    @Override
    public void add(E value) {
        modCount++;
        Node<E> prev = lastNode;
        prev.setCurrentElement(value);
        lastNode = new Node<E>(null, prev, null);
        prev.setNextElement(lastNode);
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = firstNode.getNextElement();
        for (int i = 0; i < index; i++) {
            rsl = rsl.getNextElement();
            }
        return rsl.getCurrentElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int exceptedModCount = modCount;
            int cursor = 0;
            private Node<E> rsl = firstNode;

            @Override
            public boolean hasNext() {
                return cursor < size && modCount != 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                cursor++;
                rsl = rsl.getNextElement();
                return rsl.getCurrentElement();
            }
        };
    }
}