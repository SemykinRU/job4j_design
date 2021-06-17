package ru.job4j.collection;

import java.util.Iterator;
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

    public int getModCount() {
        return modCount;
    }

    public int getSize() {
        return size;
    }

    public Node<E> getFirstNode() {
        return firstNode;
    }

    public Node<E> getLastNode() {
        return lastNode;
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
        return new SimpleLinkedIterator<>(this);
    }
}