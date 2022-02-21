package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    List<FileProperty> fileInfo = new ArrayList<>();
    Set<String> duplicates = new HashSet<>();
    int count = -1;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        count++;
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        for (FileProperty fileProperty : fileInfo) {
            if (fileProperty.getSize() == Files.size(file)
                    && fileProperty.getName().equals(file.getFileName().toString())) {
                duplicates.add(fileProperty.getPath());
                duplicates.add(file.toAbsolutePath().toString());
                break;
            }
        }
        fileInfo.add(new FileProperty(Files.size(file), file.getFileName().toString(), file.toAbsolutePath().toString()));
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        count--;
        if (count == -1) {
            for (String s : duplicates) {
                System.out.println(s);
            }
        }
        return super.postVisitDirectory(dir, exc);
    }
}
