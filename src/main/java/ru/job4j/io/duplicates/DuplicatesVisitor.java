package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<Path> duplicate = new HashSet<>();
    Map<FileProperty, Path> filePropertyPathMap = new HashMap<>();

    public Set<Path> getDuplicate() {
        return duplicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (filePropertyPathMap.containsKey(fileProperty)) {
            duplicate.add(file);
            duplicate.add(filePropertyPathMap.get(fileProperty));
        } else {
            filePropertyPathMap.put(fileProperty, file);
        }
        return super.visitFile(file, attrs);
    }
}