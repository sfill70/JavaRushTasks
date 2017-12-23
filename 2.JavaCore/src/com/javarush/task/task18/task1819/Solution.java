package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1= reader.readLine();
        String file2= reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(file1  /*"c:/1.txt"*/);
        FileInputStream inputStream2 = new FileInputStream(file2  /*"c:/2.txt"*/);

        byte array[]=new byte[inputStream.available()]; // массив 1 файла
        byte array2[]=new byte[inputStream2.available()]; // массив 2 файла
        // заполняем массив 1 файла
        while (inputStream.available()>0){
            inputStream.read(array);
        }
        //заполняем массив 2 файла
        inputStream.close();
        while (inputStream2.available()>0){
            inputStream2.read(array2);
            }
        inputStream2.close();

        /*long l=0;
        RandomAccessFile fc = new RandomAccessFile(file1, "rw");
        fc.write(array2,0, array2.length);
        fc.close();
        fc = new RandomAccessFile(file1, "rw");
        l=fc.length();
        fc.seek(l);
        fc.write( array);
        fc.close();*/
       /* System.out.println(Arrays.toString(array) );
        System.out.println(Arrays.toString(array2) );*/

        FileOutputStream outputStream2 = new FileOutputStream(file1,false /*"c:/result.txt"*/);
        outputStream2.write (array2);
        FileOutputStream outputStream = new FileOutputStream(file1,true /*"c:/result.txt"*/);
        outputStream.write (array);

        outputStream.close();
        outputStream2.close();

    }
}
