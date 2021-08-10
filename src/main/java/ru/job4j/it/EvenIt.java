package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {
    private final int[] numbers;
    private int point = 0;

    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        for (int i = point; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                point = i;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[point++];
    }
}