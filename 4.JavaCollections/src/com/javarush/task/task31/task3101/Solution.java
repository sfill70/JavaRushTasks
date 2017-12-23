package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {
    public static TreeSet<File> setFiles = new TreeSet<>(new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return o1.getName().compareTo(o2.getName()) ;
        }
    });
    public static void main(String[] args) throws IOException{
        for(String s: args)
            System.out.println(s);
        File path = new File(args[0]); //Путь к директории
        File resultFileAbsolutePath = new File(args[1]); //Файл с контекстом всех файлом <50
        File allFilesContent = new File(resultFileAbsolutePath.getParent()+"/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileOutputStream fos = new FileOutputStream(allFilesContent);
       /* BufferedWriter writer = new BufferedWriter(new FileWriter(allFilesContent))*/
        ) {
            deepSearch(path);

            byte[] buffer = new byte[1024];
            for (File file: setFiles) {
                FileInputStream fis= new FileInputStream(file);
                while (fis.available()>0){
                    int count = fis.read(buffer);
                    fos.write(buffer, 0, count);
                }
                byte[] lineSeparator = System.lineSeparator().getBytes();
                fos.write(lineSeparator);
                fis.close();
                //  System.out.println(file.getAbsoluteFile());
            }
            /*for (File file : setFiles) {

                BufferedReader reader = new BufferedReader(new FileReader(file));
                System.out.println(file.getAbsoluteFile());
                while (reader.ready()) {
                    writer.write(reader.readLine());

                    writer.newLine();
                }
                reader.close();
            }*/

        }
    }
    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
    public static void deepSearch(File path){
               if(path.isDirectory()){
            for(File file: path.listFiles()){
                deepSearch(file);
            }
        }
        else if(path.isFile()){
            if(path.length() > 50)
                FileUtils.deleteFile(path);
            else
                setFiles.add(path);
        }
    }

}