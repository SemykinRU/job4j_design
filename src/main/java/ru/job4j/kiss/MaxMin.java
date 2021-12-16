package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    private <T> T getValue(List<T> values, Comparator<T> comparator, Predicate<Integer> predicate) {
        var rsl = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            int resultCompare = comparator.compare(rsl, values.get(i));
            if (predicate.test(resultCompare)) {
                rsl = values.get(i);
            }
        }
        return rsl;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, x -> x <= 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, comparator, x -> x >= 0);
    }
}