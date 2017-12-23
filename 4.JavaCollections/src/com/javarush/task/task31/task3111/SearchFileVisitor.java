package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private List <Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
    private String  partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;

    boolean iSpartOfName =false;
    boolean iSpartOfContent =false;
    boolean iSminSize =false;
    boolean iSmaxSize =false;


    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
        iSpartOfName=true;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
        iSpartOfContent=true;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        iSminSize=true;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        iSmaxSize=true;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
       /* while (true) {
            if (iSpartOfName) {
                if (file.getFileName().toString().indexOf(this.partOfName) != -1) {
                   break;   }
            }
        }*/

        if (iSpartOfName &&  (file.getFileName().toString().indexOf(this.partOfName) == -1))  {
          return CONTINUE;       }

        if (iSminSize && Files.size(file)<minSize) {
           return CONTINUE;
        }
        if (iSmaxSize && Files.size(file)>maxSize){
            return CONTINUE;
        }
        if (iSpartOfContent &&new String(Files.readAllBytes(file)).indexOf(partOfContent) == -1){
            return CONTINUE;
        }
       // System.out.println(Files.size(file));
        foundFiles.add(file);
        return  FileVisitResult.CONTINUE;

    }
}
