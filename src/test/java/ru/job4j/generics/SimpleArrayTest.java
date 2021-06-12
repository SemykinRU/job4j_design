package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(0);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        assertThat(2, is(simpleArray.get(1)));
        assertThat(3, is(simpleArray.getSize()));
    }

    @Test
    public void whenReplaceItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(1);
        simpleArray.add(3);
        assertThat(1, is(simpleArray.get(1)));
        simpleArray.set(1, 2);
        assertThat(2, is(simpleArray.get(1)));
    }

    @Test
    public void whenRemoveItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        assertThat(3, is(simpleArray.get(1)));
        assertThat(2, is(simpleArray.getSize()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.get(0);
    }

    @Test
    public void whenIteratorHasNext() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertTrue(iterator.hasNext());
        assertThat(1, is(iterator.next()));
        assertThat(2, is(iterator.next()));
        assertThat(3, is(iterator.next()));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenIteratorNotHasNext() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        Iterator<Integer> iterator = simpleArray.iterator();
        assertFalse(iterator.hasNext());
    }
}