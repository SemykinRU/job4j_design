package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        Predicate<String> predicate = s -> {
            String str = s.trim();
            if (str.startsWith("#") || !str.contains("=")) {
                return false;
            }
            if (str.startsWith("=")) {
                throw new IllegalArgumentException();
            }
            return true;
        };
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
           var maps = reader.lines()
                   .filter(predicate)
                   .map(x -> x.split("=", 2))
                   .collect(Collectors.toMap(x -> x[0], x -> x[1]));
           values.putAll(maps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}