package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        if (!Files.isDirectory(Path.of(cachingDir))) {
            throw new IllegalArgumentException(String.format("%s директория не найдена.", cachingDir));
        }
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = "";
        try {
            rsl = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            System.out.printf("%s ошибка чтения файла.%n", e.getMessage());
        }
        return rsl;
    }
}