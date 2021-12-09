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
        String file = String.format("%s/%s", cachingDir, key);
        if (!Files.exists(Path.of(file))) {
            throw new IllegalArgumentException("Файл не найден.");
        }
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines()
                    .map(x -> x.concat(System.lineSeparator()))
                    .forEach(builder::append);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}