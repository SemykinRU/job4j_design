package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> implements Iterable<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return linked.iterator().hasNext();
            }

            @Override
            public T next() {
                return linked.iterator().next();
            }
        };
    }
}
