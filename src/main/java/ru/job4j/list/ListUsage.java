package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.add(1, "four");
        /*List<String> list = new ArrayList<>();
        list.add("five");
        list.add("six");
        rsl.addAll(2, list);
        rsl = List.of("one", "two", "three");
        //rsl.add(0, "zero");  = UnsupportedOperationException - immutable collections - List.of - только для тестов!!
        for (String s : rsl) {
            System.out.println("Текущий элемент: " + s);
        }*/
        rsl.set(1, "four and forth");
        ListIterator<String> iterator = rsl.listIterator(1);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}