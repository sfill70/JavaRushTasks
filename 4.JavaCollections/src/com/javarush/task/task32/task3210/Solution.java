package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {

        String fileName = args [0];
        long number = Long.valueOf(args [1]);
        String text =args [2];

        byte [] ar = text.getBytes();
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.seek(number);
        byte[] array=new byte[ar.length];
        raf.read(array,0,text.getBytes().length);
        String textFromFile = new String(array);
        raf.seek(raf.length());
        if (textFromFile.equals(text)){
            raf.write("true".getBytes());
        }
        else {
            raf.write("false".getBytes());}
            raf.close();


       /* try (RandomAccessFile file = new RandomAccessFile(fileName, "rw")) {
            byte[] buf = new byte[text.length()];
            file.seek(number);
            file.read(buf, 0, buf.length);
            String lineFromFile = new String(buf);
            String storeToFile = lineFromFile.equals(text) ? "true" : "false";
            file.seek(file.length());
            file.write(storeToFile.getBytes());
        }*/
    }
}
