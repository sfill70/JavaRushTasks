package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }
    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
        String tmp= new String();
        int temp=0;
        while (file1reader.ready()){
        tmp=file1reader.readLine();
                String[]array=tmp.split(" ");
            for (int i = 0; i <array.length ; i++) {
                try {
                    temp=Integer.parseInt(array[i]);
                    if(map.containsKey(temp)){
                        System.out.print(map.get(temp)+" ");
                    }
                    else {
                        System.out.print(temp+" ");
                    }
                } catch (NumberFormatException e) {
                    System.out.print(array[i]+" ");
                }
            }
        }
        file1reader.close();

    }
}
