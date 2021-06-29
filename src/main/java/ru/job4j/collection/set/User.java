package ru.job4j.collection.set;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("User", 2, new GregorianCalendar());
        User user2 = new User("User", 2, new GregorianCalendar());
        Map<User, Object> userObjectMap = new HashMap<>();
        userObjectMap.put(user1, new Object());
        userObjectMap.put(user2, new Object());
        for (var item : userObjectMap.keySet()) {
            System.out.println(item);
        }

    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }
}
