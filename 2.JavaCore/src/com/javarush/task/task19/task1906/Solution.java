package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        String file1 = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(file);
        FileWriter fileWriter = new FileWriter(file1);
       int count=0;
        while (fileReader.ready()) //пока есть непрочитанные байты в потоке ввода
        {   count++;
            int data = fileReader.read();
        if(count%2==0) {
             //читаем один символ (char будет расширен до int)
            fileWriter.write(data); //пишем один символ (int будет обрезан/сужен до char)
        }
        }

        //закрываем потоки после использования
        fileReader.close();
        fileWriter.close();
    }
}
