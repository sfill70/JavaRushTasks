package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
/*

        String fileName = args[0];
        String zipFileName = args[1];
        File file = new File(fileName);

        Map<String, ByteArrayOutputStream> archivedFiles = new HashMap<>();
        try (ZipInputStream zipReader = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zipReader.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipReader.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);

                archivedFiles.put(entry.getUser(), byteArrayOutputStream);
            }
        }

        try (ZipOutputStream zipWriter = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : archivedFiles.entrySet()) {
                if (pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1).equals(file.getUser())) continue;
                zipWriter.putNextEntry(new ZipEntry(pair.getKey()));
                zipWriter.write(pair.getValue().toByteArray());
            }

            ZipEntry zipEntry = new ZipEntry("new/" + file.getUser());
            zipWriter.putNextEntry(zipEntry);
            Files.copy(file.toPath(), zipWriter);
        }
*/


        String filePathString = args [0];
        String zipPathString = args [1];
        Path filePath = Paths.get(filePathString);
        Path zipPath = Paths.get(zipPathString);
        List <ZipEntry> zipEntries = new ArrayList<>();

        FileInputStream insZipFile = new FileInputStream(zipPathString);
        ZipInputStream zis = new ZipInputStream(insZipFile);


        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry!=null) {
            zipEntries.add(zipEntry);
            zipEntry = zis.getNextEntry();
        }

       /*zipEntries.add(zis.getNextEntry());
        while (zis.available()==1){
            zipEntries.add(zis.getNextEntry());
        }*/


        ZipEntry zipFile = new ZipEntry("/new"+filePath.getFileName().toString());


        for (ZipEntry entry : zipEntries) {
            if (filePath.getFileName().toString()==Paths.get(entry.getName()).getFileName().toString()){
                zipEntries.remove(entry);
                zipEntries.add(zipFile);
            }
            else {zipEntries.add(zipFile);}
        }
        FileOutputStream ousZipFile = new FileOutputStream(zipPathString);
        ZipOutputStream zip = new ZipOutputStream(ousZipFile);


        for (ZipEntry entry : zipEntries) {
            zip.putNextEntry (entry);
            Files.copy(zipPath,zip);
        }




    }
}
