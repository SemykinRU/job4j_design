package ru.job4j.searcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Finder {
    private final ArgsName argsName;
    private Predicate<Path> predicate;

    public Finder(String[] args) {
        argsName = ArgsName.of(args);
        argsValidate(argsName);
        setPredicate();
    }

    private void setPredicate() {
        if (argsName.get("t").equals("mask")) {
            this.predicate = path -> path.toFile().getName().endsWith(argsName.get("n").substring(1));
        }
        if (argsName.get("t").equals("name")) {
            this.predicate = path -> path.toFile().getName().equals(argsName.get("n"));
        }
        if (argsName.get("t").equals("regex")) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            this.predicate = path -> pattern.matcher(path.toFile().getName()).find();
        }
    }

    public void find() throws IOException {
        FinderFiles files = new FinderFiles(predicate);
        Files.walkFileTree(Path.of(argsName.get("d")), files);
        saveToFile(files.getFiles());
    }

    private void argsValidate(ArgsName argsName) {
        if (argsName.getSize() != 4) {
            throw new IllegalArgumentException("Приложение работает со следующими аргументами"
                    + "\n-d - директория, в которой начинать поиск"
                    + "\n-n - имя файла, маска, либо регулярное выражение."
                    + "\n-t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению."
                    + "\n-o - результат записать в файл.");
        }
        if (!Files.exists(Path.of(argsName.get("d"))) || !Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Параметр %s не найден или не является директорией", argsName.get("d")));
        }
    }

    private void saveToFile(List<Path> pathList) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(argsName.get("o")))) {
            for (var s : pathList) {
                bos.write(s.toAbsolutePath().toString().getBytes());
                bos.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
