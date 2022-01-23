package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        boolean rst = true;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
        } else {
            if (table[index].key.equals(key)) {
                table[index] = new MapEntry<>(key, value);
                count++;
                modCount++;
            } else {
                rst = false;
            }
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
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
        if (table[index] == null) {
            rst = false;
        } else {
            table[index] = null;
            count--;
            modCount++;
        }
        return rst;
    }

    @Override
    public Iterator<K> iterator() throws ConcurrentModificationException, NoSuchElementException {
        Iterator<K> iterator = new Iterator<>() {
            int i = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() throws ConcurrentModificationException {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("The Map cannot be modified while iterating");
                }
                return i < count;
            }

            @Override
            public K next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
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
