package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args should not be empty");
        }
        for (String s : args) {
            String[] val = s.split("=", 2);
            if (val.length < 2 || val[0].length() < 2 || !val[0].startsWith("-") || val[1].length() < 1) {
                throw new IllegalArgumentException("Args should have key and value separated with \"=\","
                        + " key should start with \"-\"");
            }
            values.put(val[0].substring(1), val[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
