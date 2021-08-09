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
        for (int i = 0; i < numbers.length; i++) {
            if (i >= point) {
                if (numbers[i] % 2 == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (numbers[point] % 2 != 0) {
            point++;
        }
        return numbers[point++];
    }
}