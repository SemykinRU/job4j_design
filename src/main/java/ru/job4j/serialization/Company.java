package ru.job4j.serialization;

public class Company {
    private final String name;
    private final int code;

    public Company(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Company{"
                + "name='" + name + '\''
                + ", code=" + code
                + '}';
    }
}
