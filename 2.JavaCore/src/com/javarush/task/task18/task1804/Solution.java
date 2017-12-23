package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(file1);
        int[] arrBytes = new int[256];
        //   long startTime = System.currentTimeMillis();
        while (inputStream.available() > 0) arrBytes[inputStream.read()]++;

        inputStream.close();
        // Выводим отсортированный по байт-коду в обратном порядке
        int rez=1000;
        for (int i = 0; i <arrBytes.length ; i++) {
            if (i==0&&arrBytes[i]>0){rez=arrBytes[i];}

            else {
                if(arrBytes[i]<rez&&arrBytes[i]>0 ) {rez =arrBytes[i];}
            }
        }
    //    System.out.println(rez);
        for (long i = 255; i >= 0 ; i--)
            if (arrBytes[(int) i] == rez) System.out.print(i + " ");

        //  long finishTime = System.currentTimeMillis();
        //  System.out.println("\nвремя работы=" + (finishTime-startTime) + "ms.");
    }
}
