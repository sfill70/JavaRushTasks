package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
        String tmp= new String();
     //   int temp=0;
          while (file1reader.ready()){
              StringBuilder sr=new StringBuilder();
            tmp=file1reader.readLine();
        char[]array =tmp.toCharArray();
            for (int i = array.length-1; i>=0 ; i--) {
                sr.append(array[i]);
            }
              System.out.println(sr.toString());
        }
        file1reader.close();
    }
}
