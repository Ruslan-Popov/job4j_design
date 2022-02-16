package ru.job4j.io;

import java.io.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter((new FileOutputStream(target)))) {
            boolean serverDown = false;
            boolean written = false;
            String in;
            while ((in = reader.readLine()) != null) {
                if (in.contains("400") || in.contains("500")) {
                    if (serverDown) {
                        continue;
                    }
                    serverDown = true;
                    String startDown = in.substring(4);
                    if (written) {
                        writer.println();
                    }
                    writer.print(startDown + ";");
                    written = true;
                } else {
                    if (serverDown) {
                        serverDown = false;
                        String endDown = in.substring(4);
                        writer.print(endDown + ";");
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy test = new Analizy();
        test.unavailable("server.log", "unavailable.csv");
    }
}
