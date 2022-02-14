package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            Map<String, String> val = reader.lines()
                    .filter(s -> s.trim().length() > 0)
                    .filter(s -> ('#' != s.charAt(0)))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> {
                        if (s.length < 2) {
                            return "";
                        }
                        return s[1];
                    }));
            values.putAll(val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String value(String key) throws IllegalArgumentException {
        if (key == null || values.get(key) == "") {
            throw new IllegalArgumentException();
        }
        return values.get(key);
    }
}
