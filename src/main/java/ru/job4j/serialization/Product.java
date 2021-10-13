package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private int count;
    private boolean isAction;
    private Company company;
    private String[] movementHistory;

    public Product() {

    }

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

    public static void main(String[] args) throws Exception {
        Product product = new Product("Apple", 4, false, new Company("sir Djo", 992399), new String[]{"A1", "B2"});
        JSONObject jsonCompany = new JSONObject(new Company("sir Djo", 992399));
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("B2");
        JSONArray jsonMovement = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", product.name);
        jsonObject.put("count", product.count);
        jsonObject.put("company", jsonCompany);
        jsonObject.put("isAction", product.isAction);
        jsonObject.put("movementHistory", jsonMovement);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(product));
    }
}
