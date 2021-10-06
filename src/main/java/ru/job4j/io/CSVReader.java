package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public void handle(ArgsName argsName) throws Exception {
        validateArgs(argsName);
        StringBuilder builder = new StringBuilder();
        String[] filter = argsName.get("filter").split(",");
        String delimiter = argsName.get("delimiter");
        List<Integer> index = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        try (var scanner = new Scanner(new BufferedReader(new FileReader(argsName.get("path"))))) {
            String[] key = scanner.nextLine().split(delimiter);
            for (String s : filter) {
                for (int j = 0; j < key.length; j++) {
                    if (key[j].equals(s)) {
                        index.add(j);
                        builder.append(key[j]).append(delimiter);
                    }
                }
            }
            String str = builder.substring(0, builder.length() - 1);
            stringList.add(str);
            while (scanner.hasNext()) {
                builder.delete(0, builder.length());
                String[] values = scanner.nextLine().split(delimiter);
                for (Integer i : index) {
                    builder.append(values[i]).append(delimiter);
                }
                str = builder.substring(0, builder.length() - 1);
                stringList.add(str);
            }
            toConsoleOrSaveInFile(stringList, argsName);
        }
    }

    private void toConsoleOrSaveInFile(List<String> strings, ArgsName argsName) {
        if (argsName.get("out").equals("stdout")) {
            strings.forEach(System.out::println);
        } else {
            Path path = Path.of(argsName.get("out"));
            try (var buffer = new BufferedOutputStream(new FileOutputStream(path.toFile()))) {
                for (var s : strings) {
                    buffer.write((s + System.lineSeparator()).getBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void validateArgs(ArgsName argsName) {
        if (argsName.getSize() != 4) {
            throw new IllegalArgumentException("Указано не верное кол-во аргументов запуска приложения "
                    + "или один из аргументов пуст\n"
                    + "Необходимо указать 4 аргумента path, delimiter, out и filter");
        }
        Path path = Path.of(argsName.get("path"));
        if (!Files.exists(path) || Files.isDirectory(path)) {
            throw new IllegalArgumentException(path + " файл не найден или является директорией");
        }
    }

    public static void main(String[] args) throws Exception {
        CSVReader csvReader = new CSVReader();
        csvReader.handle(ArgsName.of(args));
    }
}
