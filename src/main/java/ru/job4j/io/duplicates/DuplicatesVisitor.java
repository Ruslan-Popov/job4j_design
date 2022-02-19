package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    /*Map<Long, String> filesInfo = new HashMap<>();*/
    List<FileProperty> fileInfo = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        for (FileProperty fileProperty : fileInfo) {
            if (fileProperty.getSize() == Files.size(file) && fileProperty.getName().equals(file.getFileName().toString())) {
                System.out.printf("Duplicate found: file %s with size %d bytes%s"
                                + "%3s and file %s with same size%s",
                        fileProperty.getPath(), fileProperty.getSize(), System.lineSeparator(), "", file.toAbsolutePath().toString(), System.lineSeparator()
                        );
            }
        }
        fileInfo.add(new FileProperty(Files.size(file), file.getFileName().toString(), file.toAbsolutePath().toString()));
/*        for (Map.Entry entry : filesInfo.entrySet()) {
            if (Objects.equals(entry.getKey(), Files.size(file))
                    && entry.getValue().equals(file.getFileName().toString())) {
                System.out.printf("Duplicate found: file %s with size %d bytes%s",
                        entry.getValue(), entry.getKey(), System.lineSeparator());
            }
        }
        filesInfo.put(Files.size(file), file.getFileName().toString());*/
        return super.visitFile(file, attrs);
    }
}
