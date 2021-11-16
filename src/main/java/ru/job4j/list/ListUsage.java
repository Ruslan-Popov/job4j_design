package ru.job4j.list;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        Iterator iterator = rsl.iterator();
        Iterator iterator1 = rsl.iterator();
        System.out.println(iterator1.next());
    }
}