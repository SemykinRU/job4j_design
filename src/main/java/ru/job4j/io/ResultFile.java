package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int rsl;
            String stringOut;
            for (int i = 1; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    rsl = i * j;
                    stringOut = i + " * " + j + " = " + rsl + "\n";
                    out.write(stringOut.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
