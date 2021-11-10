package ru.job4j.garbage;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    @Override
    public void finalize() {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public static void main(String[] args) {
        User user1 = new User("user1", 10);
        User user2 = new User("2", 11111);
        User user3 = new User("Useeeerrrrrr3333", 0);

        for (int i = 0; i < 3000; i++) {
            System.out.println(new User("N" + i, i));
        }
    }
}
