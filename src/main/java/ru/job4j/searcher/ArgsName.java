package ru.job4j.searcher;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();
    private int size = 0;

    public String get(String key) {
        return values.get(key);
    }

    public int getSize() {
        return size;
    }

    public void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не указаны аргумент(ы) запуска приложения!");
        }
        for (var s : args) {
            if (!s.contains("=") || !s.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Аргумент %s не отвечает формату -агрумент=значение", s));
            }
            String[] str = s.split("=", 2);
            if (str[0].substring(1).isEmpty() || str[1].isEmpty()) {
                throw new IllegalArgumentException(String.format("В аргументе %s один из параметров пуст", s));
            }
            values.put(str[0].substring(1), str[1]);
            size++;
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
