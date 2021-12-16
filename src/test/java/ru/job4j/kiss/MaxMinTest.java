package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.hamcrest.core.Is.is;

public class MaxMinTest {

    @Test
    public void whenFindIntMaxThen() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(3);
        MaxMin max = new MaxMin();
        Comparator<Integer> comparator = Integer::compareTo;
        Assert.assertThat(max.max(list, comparator), is(10));


    }

    @Test
    public void whenFindIntMinThen() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(3);
        MaxMin max = new MaxMin();
        Comparator<Integer> comparator = Integer::compareTo;
        Assert.assertThat(max.min(list, comparator), is(1));
    }

    @Test
    public void whenFindStringMaxThen() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("e");
        list.add("z");
        MaxMin max = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        Assert.assertThat(max.max(list, comparator), is("z"));


    }

    @Test
    public void whenFindStringMinThen() {
        ArrayList<String> list = new ArrayList<>();
        list.add("b");
        list.add("d");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("e");
        list.add("z");
        list.add("a");
        list.add("a");
        list.add("a");
        MaxMin max = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        Assert.assertThat(max.min(list, comparator), is("a"));
    }
}