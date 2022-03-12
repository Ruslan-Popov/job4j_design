package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {

            List<String> phrases = readPhrases();
            List<String> log = new ArrayList<>();
            boolean pause = false;

            System.out.printf("Напишите что-нибудь, чтобы начать диалог. Напишите \"%s\", чтобы бот замолчал, "
                            + "\"%s\", чтобы продолжить, или \"%s\", чтобы завершить программу%s",
                    STOP, CONTINUE, OUT, System.lineSeparator());
            for (String input = in.readLine(); !input.equals(OUT); input = in.readLine()) {
                if (input.equals("")) {
                    continue;
                }
                if (input.equals(STOP)) {
                    pause = true;
                }
                if (input.equals(CONTINUE)) {
                    pause = false;
                }
                if (pause) {
                    continue;
                }
                log.add(input);
                PrintStream ps = new PrintStream(System.out);
                String botLine = phrases.get((int) (Math.random() * phrases.size()));
                ps.println(botLine);
                log.add(botLine);
            }
            log.add(OUT);
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader phrases = new BufferedReader(new FileReader(botAnswers))) {
            phrases.lines().forEach(strings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            out.write(log + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/botPhrases.txt");
        cc.run();
    }
}
