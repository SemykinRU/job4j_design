package ru.job4j.cache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    public static void main(String[] args) throws IOException {
        System.out.println("Укажите директорию!");
        Scanner in = new Scanner(System.in);
        DirFileCache dirFileCache = new DirFileCache(in.next());
        boolean isExit = true;
        while (isExit) {
            System.out.println("1.Получить файл.");
            System.out.println("0.Выход");
            int i = in.nextInt();
            switch (i) {
                case 1:
                    System.out.println("Имя файла");
                    String fileName = dirFileCache.get(in.next());
                    System.out.println(fileName);
                    break;
                case 0:
                    isExit = false;
                    break;
                default:
                    break;
            }
        }
    }
}
