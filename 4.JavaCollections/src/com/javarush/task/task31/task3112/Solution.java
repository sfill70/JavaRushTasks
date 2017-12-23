package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://www.amigo.com/ship/secretPassword.txt", Paths.get("E:/MyDownloads"));
      //  Path passwords = downloadFile("E:\\МосКаб1\\1.txt", Paths.get("E:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {

//Создане объекта URL
         URL url = new URL (urlString);
       // Получение имя файла "результата"
        String fileName=urlString.substring(urlString.lastIndexOf("/"));
        //Создание временного файла во временной дериктории по умолчанию
        Path createTempFile = Files.createTempFile( "temp-",".tmp");
        //Запись соденжимого в поток из URL
        InputStream inputStream = url.openStream();
       //Копирование данных из потока во временный файл
        Files.copy(inputStream, createTempFile);
       //Получение полного пути к файлу  "результату"
        Path destPath=Paths.get(downloadDirectory.toString()+"/"+ fileName);
        //Перемещение временного файла в заденнйю директорию и преобразование в файл "результат"
        Files.move(createTempFile,destPath);

        return destPath;
        // implement this method
    }
}
