package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        container[Objects.checkIndex(index, container.length - 1)] = newValue;
        return newValue;
    }

    @Override
    public T remove(int index) /*throws IndexOutOfBoundsException*/ {
        T removedElement = container[index];
        int existingIndex = Objects.checkIndex(index, size + 1);
        System.arraycopy(container, existingIndex + 1, container, existingIndex, container.length - existingIndex - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        int existingIndex = Objects.checkIndex(index, container.length);
        return container[existingIndex];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() throws NoSuchElementException, ConcurrentModificationException {
        return new Iterator<T>() {
            int i = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() throws NoSuchElementException, ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (hasNext()) {
                    return container[i++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
