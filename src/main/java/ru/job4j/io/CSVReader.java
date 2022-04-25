package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    static String out = "";

    public static void handle(ArgsName argsName) {
        out = argsName.get("out");
        try (BufferedReader input = new BufferedReader(new FileReader(argsName.get("path")))) {
            StringBuilder stringBuilder = new StringBuilder();
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
                    stringBuilder.append(firstLineWords[num]).append(System.lineSeparator());
                    break;
                }
                stringBuilder.append(firstLineWords[num]).append(";");
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(argsName.get("delimiter"));
                for (int index = 0; index < columnsNumbers.size(); index++) {
                    int colNumber = columnsNumbers.get(index);
                    if (index == columnsNumbers.size() - 1) {
                        stringBuilder.append(words[colNumber]).append(System.lineSeparator());
                        break;
                    }
                    stringBuilder.append(words[colNumber]).append(";");
                }
            }
            printOut(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOut(String in) {
        if ("stdout".equals(out)) {
            System.out.print(in);
        } else {
            try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                output.print(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void validate(ArgsName argsName) {
        String path = argsName.get("path");
        out = argsName.get("out");
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new IllegalArgumentException("input must be file");
        }
        if (!"stdout".equals(out) && !Files.isRegularFile(Paths.get(out))) {
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