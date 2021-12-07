package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> lit = list.listIterator();
        while (lit.hasNext()) {
            if (lit.nextIndex() == index) {
                lit.next();
                lit.add(value);
                break;
            }
            lit.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> lit = list.listIterator();
        while (lit.hasNext()) {
            if (filter.test(lit.next())) {
                lit.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> lit = list.listIterator();
        while (lit.hasNext()) {
            if (filter.test(lit.next())) {
                lit.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> lit = list.listIterator();
        while (lit.hasNext()) {
            T tmp = lit.next();
            for (T el : elements) {
                if (tmp.equals(el)) {
                    lit.remove();
                }
            }
        }
    }
}