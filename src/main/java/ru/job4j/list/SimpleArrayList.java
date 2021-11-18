package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void doubleArrayLength() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            doubleArrayLength();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
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
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return i < size;
            }

            @Override
            public T next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[i++];
            }
        };
    }
}
