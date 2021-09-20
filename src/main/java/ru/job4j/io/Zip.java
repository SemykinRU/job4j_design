package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final ArgsName argsName;
    private final List<Path> filesList;
    private final File target;

    public Zip(String[] args) throws IOException {
        this.argsName = ArgsName.of(args);
        checkArgs(argsName);
        this.filesList = filesList();
        this.target = new File(argsName.get("o"));
    }

    public void packAllFiles() {
        packFiles(filesList, target);
    }

    private List<Path> filesList() throws IOException {
        return Search.search(Path.of(argsName.get("d")), x -> !x.toFile().getName().endsWith(argsName.get("e")));
    }

    private void checkArgs(ArgsName argsName) {
        if (argsName.getSize() != 3) {
            throw new IllegalArgumentException("Для запуска приложения необходимо указать три параметра.\n "
                    + "Первый параметр - начальная папка которую мы хотим архивировать.\n"
                    + "Второй параметр - расширение файлов, которые необходимо исключить\n"
                    + "Третий параметр - во что мы архивируем\n");
        }
        if (!Files.exists(Path.of(argsName.get("d"))) || !Files.isDirectory(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("%s каталога не существует или аргумент не является каталогом.", argsName.get("d")));
        }
    }

    private void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var path : sources) {
                zip.putNextEntry(new ZipEntry(path.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(args);
        zip.packAllFiles();
        System.out.println("Готово!");
    }
}