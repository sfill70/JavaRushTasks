package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
        String tmp= new String();
        int count;
        while (file1reader.ready()){
            count=0;
            tmp=file1reader.readLine();
            for (int i = 0; i <words.size() ; i++) {
                if(tmp.contains(words.get(i))){
                    count++;
                }
            }
            if (count==2){
                System.out.println(tmp);
            }
        }
        file1reader.close();

    }
}
