package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean rst = !contains(value);
        if (rst) {
            set.add(value);
        }
        return rst;
    }

    @Override
    public boolean contains(T value) {
        boolean rst = false;
        for (T data : set) {
            if (Objects.equals(value, data)) {
                rst = true;
                break;
            }
        }
        return rst;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
