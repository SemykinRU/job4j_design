package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        assertThat(2, is(simpleArray.get(1)));
    }

    @Test
    public void whenReplaceItem() {
        SimpleArray<String> simpleArray = new SimpleArray<>(4);
        simpleArray.add("First");
        simpleArray.add("Second");
        simpleArray.set(3, "Third");
        assertThat("Third", is(simpleArray.get(2)));
        simpleArray.add("Fourth");
        assertThat("Fourth", is(simpleArray.get(3)));
        simpleArray.set(3, "1234");
        assertThat("1234", is(simpleArray.get(3)));
    }

    @Test
    public void whenRemoveItem() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(1);
        assertThat(3, is(simpleArray.get(1)));
        simpleArray.remove(0);
        assertThat(3, is(simpleArray.get(0)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(0);
        simpleArray.add(2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.set(10, 111111);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.remove(20);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetIndexOutOfBoundsException() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(10);
        simpleArray.get(20);
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