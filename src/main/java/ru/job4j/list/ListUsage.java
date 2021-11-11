package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class ListUsage {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add(1, "four");
        for (String s : list) {
            System.out.println("Текущий элемент: " + s);
        }
    }
}
