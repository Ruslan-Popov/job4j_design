package ru.job4j.io;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int[] values = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                values[i] = Integer.parseInt(lines[i]);
            }
            List<Integer> oddValues = new ArrayList<>();
            for (int i : values) {
                if (i % 2 == 0) {
                    oddValues.add(i);
                }
            }
            System.out.println(oddValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
