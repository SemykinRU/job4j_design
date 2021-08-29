package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String s;
            String[] str;
            while ((s = reader.readLine()) != null) {
                if (isTrueKey(s)) {
                    str = s.split("=", 2);
                    values.put(str[0], str[1].isEmpty() ? null : str[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean isTrueKey(String s) throws IllegalArgumentException {
        if (s.startsWith("#") || !s.contains("=")) {
            return false;
        }
        if (Character.isDigit(s.charAt(0)) || s.startsWith("=")) {
            throw new IllegalArgumentException();
        }
        int lastIndex = s.indexOf("=");
        String[] arrayStrings = s.split("");
        Pattern pattern = Pattern.compile("[a-zA-Z\\d]");
        for (int i = 0; i < lastIndex; i++) {
            Matcher matcher = pattern.matcher(arrayStrings[i]);
            if (!matcher.find()) {
                throw new IllegalArgumentException();
            }
        }
        return true;
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