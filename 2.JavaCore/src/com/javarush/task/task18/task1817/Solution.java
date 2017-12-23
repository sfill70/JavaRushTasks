package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.*;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FileInputStream inputStream = new FileInputStream(file/*"c:/1.txt"*/);
        byte array[]=new byte[inputStream.available()];
        inputStream.read(array);
        inputStream.close();
        double countSymbol=array.length;
       double countSpeace=0;
        for (int i = 0; i <array.length ; i++) {
            if (array[i]==32){
                countSpeace++;
            }
        }
        double result = (countSpeace*100/countSymbol);
      //  System.out.println(countSpeace + "  "+ countSymbol + " "+result);
        System.out.println(String.format(Locale.US,"%.2f",(result)));

    }
}
