package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args)throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));
        String st=new String();
        while ((st = fileReader.readLine()) != null) {
            //st=st.replaceAll("[\\p{Punct}]","");
            fileWriter.write(st.replaceAll("[\\p{Punct}]",""));
        }
        fileReader.close();
        fileWriter.close();
    }
}