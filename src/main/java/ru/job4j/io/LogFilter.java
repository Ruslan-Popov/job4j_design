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
                    .map(s -> s.split(" "))
                    .filter(s -> s[s.length - 2].equals("404"))
                    .map(s -> {
                        StringBuilder strb = new StringBuilder();
                        for (int i = 0; i < s.length; i++) {
                            strb.append(s[i] + " ");
                            if (i == s.length - 1) {
                                strb.append(System.lineSeparator());
                            }
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
        System.out.println(log);
    }
}
