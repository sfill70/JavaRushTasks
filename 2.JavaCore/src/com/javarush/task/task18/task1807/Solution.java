package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(file1);

        /*byte buffer[]  = new byte[inputStream.available()]; //Задаем размер массива, считываем данные в память
        inputStream.read(buffer); //Пишем данные бобайтно в массив*/

        int count = 0;

        while (inputStream.available() > 0) {
            if (inputStream.read() == 44) {
                count++;
            }
        }
        inputStream.close();

        System.out.println(count);


        //c:/data.txt

       /*int count1=0;
        for (int i = 0; i <buffer.length ; i++) {
            if(buffer[i]== 44){count1 ++;
            }
        }
        System.out.println(count1);*/
    }
}
