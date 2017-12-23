package com.javarush.task.task31.task3113;



import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution extends SimpleFileVisitor<Path> {
    static int countFiles=0;
    static int countDirectories=0;
    static int sizeDirectory=0;
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        countFiles++;
        sizeDirectory+=Files.size(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        countDirectories++;

        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        Path directory = Paths.get(path);
        reader.close();
        if (!Files.isDirectory(directory)) {
            System.out.printf(directory.toAbsolutePath().toString() + " - не папка");
            return;
        }
        Files.walkFileTree(directory, new Solution());

        System.out.println( "Всего папок - "+ (countDirectories-1));
        System.out.println( "Всего файлов - "+countFiles);
        System.out.println( "Общий размер - "+sizeDirectory);
//   c:\Qwert
    }
}
