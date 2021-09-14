package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Для запуска приложения необходимо указать два параметра. "
                    + "Первый параметр - начальная папка. "
                    + "Второй параметр - расширение файлов");
        }
        if (!Files.exists(Path.of(args[0])) || !Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format("%s каталога не существует или аргумент не является каталогом.", args[0]));
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}