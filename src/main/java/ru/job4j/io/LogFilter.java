package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines()
                    .map(string -> string.split(" "))
                    .filter(stringArray -> "404".equals(stringArray[stringArray.length - 2]))
                    .map(stringArray -> {
                        StringBuilder strb = new StringBuilder();
                        for (String word : stringArray) {
                            strb.append(word + " ");
                        }
                        return strb.toString();
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(file)))) {
            for (String s : log) {
                out.printf("%s%n", s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
