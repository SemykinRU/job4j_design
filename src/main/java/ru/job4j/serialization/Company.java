package ru.job4j.serialization;

public class Company {

    private String name;
    private int code;

    public Company() {

    }

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
