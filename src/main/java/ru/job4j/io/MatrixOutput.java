package ru.job4j.io;

import java.io.FileOutputStream;

public class MatrixOutput {

    public static String multiple(int size) {
        String s = "";
        int[][] table = new int[size][size];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                table[i - 1][j - 1] = i * j;
                s = s + table[i - 1][j - 1] + " ";
            }
            s = s + System.lineSeparator();
        }
        return s;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write(multiple(10).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
