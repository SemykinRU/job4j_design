package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        String s;
        String[] str;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
           while ((s = in.readLine()) != null) {
               str = s.split(" ");
               if (str.length >= 2 && str[str.length - 2].equals("404")) {
                   rsl.add(s);
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}