package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutPutAndGetGetAndPutThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "one");
        stringSimpleMap.put(2, "two");
        assertThat(stringSimpleMap.get(1), is("one"));
        assertThat(stringSimpleMap.get(2), is("two"));
        stringSimpleMap.put(1, "1");
        assertThat(stringSimpleMap.get(1), is("1"));
    }

    @Test
    public void whenCollisionPutIsFalseThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "one");
        stringSimpleMap.put(2, "two");
        assertFalse(stringSimpleMap.put(9, "9"));
    }

    @Test
    public void whenRemoveTrueTrueAndFalseFalseThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "one");
        stringSimpleMap.put(2, "two");
        assertTrue(stringSimpleMap.remove(1));
        assertTrue(stringSimpleMap.remove(2));
        assertFalse(stringSimpleMap.remove(1));
        assertFalse(stringSimpleMap.remove(6));
    }

    @Test
    public void whenIteratorIsTrueThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "one");
        stringSimpleMap.put(5, "five");
        Iterator<Integer> key = stringSimpleMap.iterator();
        assertTrue(key.hasNext());
        assertThat(key.next(), is(1));
        assertThat(key.next(), is(5));
        assertFalse(key.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementExceptionThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAndConcurrentModificationExceptionThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "1");
        Iterator<Integer> key = stringSimpleMap.iterator();
        key.next();
        stringSimpleMap.put(2, "2");
        key.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenReplaceAndConcurrentModificationExceptionThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "1");
        stringSimpleMap.put(2, "2");
        Iterator<Integer> key = stringSimpleMap.iterator();
        key.next();
        stringSimpleMap.put(1, "2");
        key.next();
    }

    @Test
    public void whenExpandThen() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "1");
        stringSimpleMap.put(2, "2");
        stringSimpleMap.put(3, "3");
        stringSimpleMap.put(4, "4");
        stringSimpleMap.put(5, "5");
        stringSimpleMap.put(6, "6");
        stringSimpleMap.put(7, "7");
        stringSimpleMap.put(8, "8");
        stringSimpleMap.put(9, "9");
        stringSimpleMap.put(10, "10");
        assertThat(stringSimpleMap.get(1), is("1"));
        assertThat(stringSimpleMap.get(9), is("9"));
        assertThat(stringSimpleMap.get(10), is("10"));
    }

}