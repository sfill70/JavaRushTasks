package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file1);
        int maxByte = inputStream.read();
        int data = 0;
        // int i = 0;
        while (inputStream.available() > 0) {
            // i++;
//  System.out.println(data = inputStream.read());

            data = inputStream.read();
            if (data > maxByte) {
                maxByte = data;

            }

        }
        System.out.println(maxByte);
        inputStream.close();

    }
}
