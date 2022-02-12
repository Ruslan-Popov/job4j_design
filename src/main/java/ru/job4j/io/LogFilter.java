package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
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

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String s : log) {
            System.out.println(s);
        }
    }
}
