package ru.job4j.it;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class MatrixIt implements Iterator {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0) {
            if (row >= data.length - 1) {
                return false;
            }
            row++;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column > data[row].length - 1) {
            column = 0;
            row++;
            while (data[row].length == 0) {
                row++;
            }
            return data[row][column++];
        }
        return data[row][column++];
    }
}
