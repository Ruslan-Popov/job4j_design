package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 4, 3);
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 2, 6, 1, 8));
        Predicate<Integer> isEven = x -> x % 2 == 0;
        ListUtils.removeIf(list, isEven);
        assertThat(list, is(Arrays.asList(3, 1)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 7, 8, 3, 4, 5, 5));
        Predicate<Integer> condition = x -> x > 5;
        ListUtils.replaceIf(list, condition, 5);
        assertThat(list, is(Arrays.asList(1, 5, 5, 3, 4, 5, 5)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 2, 4));
        List<Integer> exceptList = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(list, exceptList);
        assertThat(list, is(Arrays.asList(1, 2)));
    }
}