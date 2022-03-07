package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    Path start;

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path p : sources) {
                try (FileInputStream fis = new FileInputStream(p.toFile())) {
                    zip.putNextEntry(new ZipEntry(start.getParent().relativize(p).toString()));
                    zip.write(fis.readAllBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toFile()))) {
            zip.putNextEntry(new ZipEntry(source.toString()));
            System.out.println(source.toString());
            zip.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(new String[] {args[0], args[1], args[2]});
        if (args.length != 3) {
            throw new IllegalArgumentException("Root folder, extension to exclude and target zip directory "
                    + "must be specified. Usage java -jar target/zip.jar ROOT_FOLDER EXTENSION_TO_EXCLUDE "
                    + "TARGET_ZIP_DIRECTORY");
        }
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException("Root folder must be directory");
        }
        Zip zip = new Zip();
        zip.start = Paths.get(directory);
        List<Path> in = Search.search(Paths.get(directory), Predicate.not(p -> p.getFileName().toString().endsWith(exclude)));
        zip.packFiles(in, Paths.get(output));
    }
}