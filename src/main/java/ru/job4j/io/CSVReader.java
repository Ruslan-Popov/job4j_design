package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    static String out = "";

    public static void handle(ArgsName argsName) {
        out = argsName.get("out");
        try (BufferedReader input = new BufferedReader(new FileReader(argsName.get("path")));
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
            Scanner scanner = new Scanner(input);
            String firstLine = scanner.nextLine();
            String[] firstLineWords = firstLine.split(argsName.get("delimiter"));
            List<String> columnsNames = new ArrayList<>(Arrays.asList(firstLineWords));
            String filter = argsName.get("filter");
            String[] filteredColumnNames = filter.split(",");
            List<Integer> columnsNumbers = new ArrayList<>();
            for (String s : filteredColumnNames) {
                if (columnsNames.contains(s)) {
                    columnsNumbers.add(columnsNames.indexOf(s));
                }
            }
            for (int i = 0; i < columnsNumbers.size(); i++) {
                int num = columnsNumbers.get(i);
                if (i == columnsNumbers.size() - 1) {
                    printOut(firstLineWords[num] + System.lineSeparator(), output);
                    break;
                }
                printOut(firstLineWords[num] + ";", output);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(argsName.get("delimiter"));
                for (int index = 0; index < columnsNumbers.size(); index++) {
                    int colNumber = columnsNumbers.get(index);
                    if (index == columnsNumbers.size() - 1) {
                        printOut(words[colNumber] + System.lineSeparator(), output);
                        break;
                    }
                    printOut(words[colNumber] + ";", output);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOut(String in, PrintWriter output) {
        if (out.equals("stdout")) {
            System.out.print(in);
        } else {
            output.print(in);
        }
    }

    public static void validate(ArgsName argsName) {
        String path = argsName.get("path");
        out = argsName.get("out");
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new IllegalArgumentException("input must be file");
        }
        if (!out.equals("stdout") && !Files.isRegularFile(Paths.get(out))) {
            throw new IllegalArgumentException("Output folder (out) must be either directory or stdout");
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("input file, delimiter, output folder/stdout and columns"
                    + "must be specified. Usage java -jar target/csvReader.jar -path=file.csv "
                    + "-delimiter=\";\" -out=stdout -filter=name,age");
        }
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        CSVReader.handle(argsName);
    }
}