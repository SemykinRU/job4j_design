package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> wasDeleted = head;
        head = head.next;
        return wasDeleted.value;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> wasDeleted = head;
        while (wasDeleted.next != null) {
            head = wasDeleted;
            wasDeleted = wasDeleted.next;
        }
        return wasDeleted.value;
    }

    public boolean revert() {
        Node<T> next;
        if (head == null || head.next == null) {
            return false;
        } else {
            Node<T> prev = null;
            Node<T> current = head;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                head = prev;
            }
            return true;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}