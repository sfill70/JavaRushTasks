package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) throws IOException{
        byte[] buffer = new byte[100];

            /*String file1="c:/3.txt";
            String file2="c:/1.txt";*/
        /*String file1="c:/1.txt";
        String file2="c:/data.txt";*/

            FileInputStream inputStream = new FileInputStream(args[1]);
            FileOutputStream outputStream = new FileOutputStream (args[2]);

            while (inputStream.available()>0) {
                if(inputStream.available()<100){
                    buffer =new  byte [inputStream.available()];
                }
                int readBytesCount = inputStream.read(buffer);
                reverse(buffer);
                if (readBytesCount > 0) {
                    // данные были считаны - есть, что записать
                    outputStream.write(buffer, 0, readBytesCount);
                }
            }
            outputStream.close();
            inputStream.close();



    }
    public static void reverse (byte[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        byte tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }
}
