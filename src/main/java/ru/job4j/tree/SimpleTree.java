package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    final private Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    public boolean isBinary() {
        return findByPredicate(x -> x.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(child).isPresent()) {
            return false;
        }
        Optional<Node<E>> item = findBy(parent);
        if (item.isEmpty()) {
            return false;
        }
        item.get().children.add(new Node<>(child));
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> x.value.equals(value));
    }
}