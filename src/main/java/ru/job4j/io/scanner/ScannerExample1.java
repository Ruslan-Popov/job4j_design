package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) {
        String ls = System.lineSeparator();
        String data = String.join(ls,
                "1 2 3",
                "4 5 6",
                "7 8 9");
        System.out.println(data);
        Scanner scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNext()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
    }
}
