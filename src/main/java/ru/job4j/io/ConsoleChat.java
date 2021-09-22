package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private final String out = "закончить";
    private final String stop = "стоп";
    private final String continues = "продолжить";
    private List<String> log = new ArrayList<>();


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean stopBot = false;
        boolean close = true;
        String str;
        Scanner in = new Scanner(System.in);
        while (close) {
            str = in.next();
            if (str.equals(out)) {
                log.add(str);
                close = false;
                saveLog(log);
                continue;
            }
            if (str.equals(stop)) {
                stopBot = true;
                log.add(str);
                continue;
            }
            if (str.equals(continues)) {
                stopBot = false;
                log.add(str);
                continue;
            }
            if (!stopBot) {
                botRun(str);
            }
        }
    }

    private void botRun(String str) {
        log.add(str);
        str = answer();
        log.add(str);
        System.out.println(str);
    }

    private String answer() {
        List<String> answers = readPhrases();
        int lastIndex = answers.size();
        return answers.get(new Random().nextInt(lastIndex));
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(botAnswers))) {
            bfr.lines().forEach(rsl::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(path))) {
            for (var s : log) {
                bfw.write(s + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\data\\bot\\log.txt", ".\\data\\bot\\botAnswer.txt");
        cc.run();
    }
}
