package ru.job4j.collection;

public class Node<E> {

    private E currentElement;
    private Node<E> nextElement;
    private Node<E> prevElement;

    public Node(E currentElement, Node<E> prevElement, Node<E> nextElement) {
        this.currentElement = currentElement;
        this.nextElement = nextElement;
        this.prevElement = prevElement;
    }

    public E getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(E currentElement) {
        this.currentElement = currentElement;
    }

    public Node<E> getNextElement() {
        return nextElement;
    }

    public void setNextElement(Node<E> nextElement) {
        this.nextElement = nextElement;
    }

    public Node<E> getPrevElement() {
        return prevElement;
    }

    public void setPrevElement(Node<E> prevElement) {
        this.prevElement = prevElement;
    }
}
