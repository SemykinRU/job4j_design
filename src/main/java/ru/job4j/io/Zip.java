package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
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

    public static void packSingleFile(File source, File target) {
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
        ArgsName argsName = ArgsName.of(args);
        Zip.packFiles(Search.search(Path.of(argsName.get("d")), x -> x.toFile().getName().endsWith(argsName.get("e"))), new File(argsName.get("o")));
    }
}