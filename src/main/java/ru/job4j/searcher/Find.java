package ru.job4j.searcher;

import java.io.IOException;

public class Find {
    public static void main(String[] args) throws IOException {
        Finder finder = new Finder(args);
        finder.find();
    }
}
