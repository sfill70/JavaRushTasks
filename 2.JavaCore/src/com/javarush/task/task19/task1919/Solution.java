package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
    String fileName = args[0];
        TreeMap<String,Double> statement=new TreeMap<>();
    BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
       String name=new String();
       String tmp= new String();
       double salary = 0;
        while (file1reader.ready()){
            tmp = file1reader.readLine();
            name = tmp.substring(0,tmp.indexOf(" ")).replaceAll("[^a-zA-Zа-яА-я}]","").trim();
            salary = Double.parseDouble(tmp.substring(tmp.indexOf(" "),tmp.length()).replaceAll(" ","").trim());
            if (statement.containsKey(name)){
                salary=salary+statement.get(name);
                statement.put(name,salary);
            }
            else {statement.put(name,salary);}
        }
        file1reader.close();
        for (Map.Entry<String,Double> pair : statement.entrySet()) //Короткая запись
        {
            String key = pair.getKey();
            double value = pair.getValue();
            System.out.println(key + " " + value);
        }
    }

}
