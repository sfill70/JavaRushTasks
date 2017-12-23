package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Sfill on 25.07.2017.
 */
public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        else {
        return (f.getName().toLowerCase().endsWith(".html")||f.getName().toLowerCase().endsWith(".htm"));}
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
