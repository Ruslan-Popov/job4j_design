package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                System.out.println(line);
            }
            /*            in.lines().forEach(System.out::println);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}