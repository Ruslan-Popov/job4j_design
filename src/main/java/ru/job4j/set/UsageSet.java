package ru.job4j.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UsageSet {
    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        Iterator<String> iterator = strings.iterator();
        for (String s : strings) {
            System.out.println("Текущий элемент: " + s);
        }
        strings.removeIf(s -> s.startsWith("t"));
        System.out.println("Вывод в консоль после удаления... ");
        for (String s : strings) {
            System.out.println("Текущий элемент: " + s);
        }
    }
}
