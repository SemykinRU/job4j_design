package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return row < data.length && column < data[data.length - 1].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        for (int i = row; i < data.length; i++) {
            if (data[row].length > 0) {
                break;
            }
            row++;
        }
        Integer rsl = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }
}