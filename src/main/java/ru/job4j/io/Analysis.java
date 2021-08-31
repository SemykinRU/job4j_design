package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) throws FileNotFoundException {
        List<String> in = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(in::add);
            List<String> out = new ArrayList<>(converted(in));
            saveStringFile(target, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveStringFile(String path, List<String> list) {
        try (FileWriter writer = new FileWriter(path)) {
            for (var s : list) {
                writer.write(s + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> converted(List<String> in) {
        String[] arrayStrings = new String[in.size()];
        in.toArray(arrayStrings);
        String[] forBuild;
        int end = arrayStrings.length;
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < end; i++) {
            if (arrayStrings[i].startsWith("400") || arrayStrings[i].startsWith("500")) {
                builder.delete(0, builder.length());
                forBuild = arrayStrings[i].split(" ");
                builder.append(forBuild[1]).append(";");
                for (int j = i; j < end; j++) {
                    if (!arrayStrings[j].startsWith("400") && !arrayStrings[j].startsWith("500")) {
                        forBuild = arrayStrings[j].split(" ");
                        builder.append(forBuild[1]).append(";");
                        break;
                    }
                    i = j;
                }
                result.add(builder.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Analysis analysis = new Analysis();
        analysis.unavailable("./log/log.txt", "./log/result.txt");
    }
}
