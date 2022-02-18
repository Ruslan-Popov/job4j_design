package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsolutePath()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsolutePath()));
        }
        System.out.println(String.format(file.getName() + " folder's size : %s", file.getTotalSpace()));
        System.out.println("Files / folders : ");
        for (File subfile : file.listFiles()) {
            System.out.println(subfile.getName() + " , size : " + subfile.length());
        }
    }
}
