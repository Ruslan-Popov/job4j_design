package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        SimpleMap<Integer, String> sMap = new SimpleMap<>();
        sMap.put(1, "First");
        assertThat(sMap.get(1), is("First"));
    }

    @Test
    public void whenGetCount() {
        SimpleMap<Integer, String> sMap = new SimpleMap<>();
        sMap.put(1, "First");
        sMap.put(2, "Second");
        sMap.put(3, "Third");
        assertThat(sMap.getCount(), is(3));
    }

    @Test
    public void whenExpandThenCapacityDouble() {
        SimpleMap<Integer, String> sMap = new SimpleMap<>();
        sMap.put(1, "First");
        sMap.put(2, "Second");
        sMap.put(3, "Third");
        sMap.put(4, "Fourth");
        sMap.put(5, "Fifth");
        sMap.put(6, "Sixth");
        sMap.put(7, "Seventh");
        assertThat(sMap.getCapacity(), is(16));
    }

    @Test
    public void whenGetThenExpandThenGetWithCollisions() {
        SimpleMap<String, String> sMap = new SimpleMap<>();
        sMap.put("Первый", "First");
        sMap.put("Второй", "Second");
        assertThat(sMap.get("Второй"), is("Second"));
        sMap.put("Третий", "Third");
        sMap.put("Четвертый", "Fourth");
        sMap.put("Пятый", "Fifth");
        sMap.put("Шестой", "Sixth");
        sMap.put("Седьмой", "Seventh");
        sMap.put("Восьмой", "Eighth");
        sMap.put("Девятый", "Ninth");
        sMap.put("Десятый", "Tenth");
        System.out.println(sMap);
        assertThat(sMap.get("Второй"), is("Second"));
        assertThat(sMap.get("Восьмой"), is(nullValue()));
        assertThat(sMap.get("Седьмой"), is(nullValue()));
        assertThat(sMap.get("Девятый"), is("Ninth"));
        assertThat(sMap.get("Десятый"), is(nullValue()));
    }

    @Test
    public void whenReplace() {
        SimpleMap<String, String> sMap = new SimpleMap<>();
        sMap.put("Первый", "First");
        sMap.put("Второй", "Second");
        sMap.put("Второй", "Segundo");
        assertThat(sMap.get("Второй"), is("Second"));
    }

    @Test
    public void whenRemoveThenGetValueIsNull() {
        SimpleMap<String, String> sMap = new SimpleMap<>();
        sMap.put("Первый", "First");
        sMap.put("Второй", "Second");
        sMap.remove("Второй");
        assertThat(sMap.get("Второй"), is(nullValue()));
    }

    @Test
    public void whenRemoveThenCountDecrease() {
        SimpleMap<String, String> sMap = new SimpleMap<>();
        sMap.put("Первый", "First");
        sMap.put("Второй", "Second");
        sMap.remove("Второй");
        assertThat(sMap.getCount(), is(1));
    }
}