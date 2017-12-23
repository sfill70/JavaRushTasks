package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(file1);
        FileOutputStream outputStream = new FileOutputStream(file2);
        FileOutputStream outputStream2 = new FileOutputStream(file3);

        //c:/data.txt
      // "c:/result.txt"
        // "c:/4.txt"
        if (inputStream.available()>0) {
           // System.out.println(inputStream.available());
            byte buffer1[]  = new byte[inputStream.available()-inputStream.available()/2]; //Задаем размер массива, считываем данные в память
            byte buffer2[]  = new byte[inputStream.available()/2]; //Задаем размер массива, считываем данные в память
            inputStream.read(buffer1); //Пишем данные бобайтно в массив
            inputStream.read(buffer2); //Пишем данные бобайтно в массив
            outputStream.write(buffer1);
            outputStream2.write(buffer2);
        }
        inputStream.close();
        outputStream.close();
        outputStream2.close();

    }
}
