package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    public int getCount() {
        return count;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "SimpleMap{"
                + "table=" + Arrays.toString(table)
                + '}';
    }

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        boolean rst = true;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        } else {
            rst = false;
        }
        return rst;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] newTable = new MapEntry[capacity * 2];
        capacity = capacity * 2;
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = indexFor(hash(entry.key.hashCode()));
                newTable[index] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rst = null;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            if (table[index].key.equals(key)) {
                rst = table[index].value;
            }
        }
        return rst;
    }

    @Override
    public boolean remove(K key) {
        boolean rst = true;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null || !table[index].key.equals(key)) {
            rst = false;
        } else {
            table[index] = null;
            count--;
            modCount++;
        }
        return rst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity
                && count == simpleMap.count
                && modCount == simpleMap.modCount
                && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = 24;
        result = 31 * result + capacity;
        result = 31 * result + count;
        result = 31 * result + modCount;
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    @Override
    public Iterator<K> iterator() throws ConcurrentModificationException, NoSuchElementException {
        Iterator<K> iterator = new Iterator<>() {
            int point = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The Map cannot be modified while iterating");
                }
                boolean rst = false;
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        point = i;
                        rst = true;
                        break;
                    }
                }
                return rst;
            }

            @Override
            public K next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
        return iterator;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }
    }
}
