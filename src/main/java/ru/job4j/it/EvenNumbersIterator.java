package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] arrays;
    private int point = 0;

    public EvenNumbersIterator(int[] ints) {
        this.arrays = ints;
    }

    public boolean isHaveEvenNumbers(int point, int[] arrays) {
        boolean rsl = false;
        for (int i = point; i < arrays.length; i++) {
            if (arrays[i] % 2 == 0) {
                this.point = i;
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return point < arrays.length && isHaveEvenNumbers(point, arrays);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return arrays[point++];
    }
}
