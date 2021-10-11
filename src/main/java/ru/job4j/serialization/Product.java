package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Product {
    private final String name;
    private final int count;
    private final boolean isAction;
    private final Company company;
    private final String[] movementHistory;

    public Product(String name, int count, boolean isAction, Company company, String[] movementHistory) {
        this.name = name;
        this.count = count;
        this.isAction = isAction;
        this.company = company;
        this.movementHistory = movementHistory;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public boolean isAction() {
        return isAction;
    }

    public Company getCompany() {
        return company;
    }

    public String[] getMovementHistory() {
        return movementHistory;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", count=" + count
                + ", isAction=" + isAction
                + ", company=" + company
                + ", movementHistory=" + Arrays.toString(movementHistory)
                + '}';
    }

    public static void main(String[] args) {
        Product product = new Product("Apple", 4, false, new Company("sir Djo", 992399), new String[]{"A1", "B2"});
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(product));
        String stringJson = "{"
                + "\"name\":\"Apple\","
                + "\"count\":4,"
                + "\"isAction\":false,"
                + "\"company\":"
                    + "{"
                        + "\"name\":\"sir Djo\","
                        + "\"code\":992399"
                        + "},"
                + "\"movementHistory\":"
                    + "[\"A1\",\"B2\"]"
                + "}";
        Product productFromJson = gson.fromJson(stringJson, Product.class);
        System.out.println(productFromJson);
    }
}
