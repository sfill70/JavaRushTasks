package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        TreeMap<String,Double> statement=new TreeMap<>();
        BufferedReader file1reader = new BufferedReader(new FileReader(fileName));
        String name=new String();
        String tmp= new String();
        double salary = 0;
        /*String pattern = "[а-яА-Я]+";
        Pattern p = Pattern.compile(pattern);*/
        while (file1reader.ready()){
            tmp = file1reader.readLine();
            /*Matcher m = p.matcher(tmp);
            if(m.find())
            {System.out.println(m.group());}*/
            name = tmp.substring(0,tmp.indexOf(" ")).replaceAll("[^a-zA-Zа-яА-я}]","").trim();
            salary = Double.parseDouble(tmp.substring(tmp.indexOf(" "),tmp.length()).replaceAll(" ","").trim());
            if (statement.containsKey(name)){
                salary=salary+statement.get(name);
                statement.put(name,salary);
            }
            else {statement.put(name,salary);}
        }
        file1reader.close();
        double max=0;
        for (Double sal : statement.values()) {
            if (max <sal) max = sal;
        }
        for (Map.Entry<String, Double> entry : statement.entrySet()) {
            if (entry.getValue() == max) System.out.println(entry.getKey());
        }
    }

}
