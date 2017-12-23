package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FileInputStream inputStream = new FileInputStream(file/*"c:/1.txt"*/);
        byte array[]=new byte[inputStream.available()];
        inputStream.read(array);
        inputStream.close();
       // char[] array = fileName.toCharArray();
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if ((64 < array[i] && array[i] < 91) || (96 < array[i] && array[i] < 123)) {
                count++;
            }
        }
        System.out.println(count);

    }
}
