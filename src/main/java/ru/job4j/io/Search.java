package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder and extension to search must be specified. Usage java -jar target/search.jar ROOT_FOLDER EXTENSION");
        }
        Path start = Paths.get(args[0]);
        String extension = args[1];
        if (!Files.isDirectory(start)) {
            throw new IllegalArgumentException("Root folder must be directory");
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Extension must start with \".\"");
        }
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
