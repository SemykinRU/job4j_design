package ru.job4j.ood.srp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExampleOne {
    private final List<String> listRepository = new ArrayList<>();
    private final Set<String> setRepository = new HashSet<>();

    public void addInList(String str) {
        listRepository.add(str);
    }

    public boolean addInStringSet(String str) {
        return setRepository.add(str);
    }
}
