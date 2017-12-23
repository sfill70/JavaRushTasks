package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];
        BufferedReader file1reader = new BufferedReader(new FileReader(fileName1));
        BufferedWriter fileWriter = new BufferedWriter (new FileWriter(fileName2));
        String tmp= new String();
        StringBuilder sr=new StringBuilder();
        while ((tmp = file1reader.readLine())!=null) {

            String[] array = tmp.split(" ");
            for (int i = 0; i < array.length; i++) {
            if (array[i].length()>6){
                /*if (!file1reader.ready()&&i==array.length-1)
                   {fileWriter.write(array[i]);}
                else {
                fileWriter.write(array[i]+","); }*/
                sr.append(array[i]);
                sr.append(",");
            }
            }
         //   System.out.println(file1reader.ready());
        }
        fileWriter.write(sr.toString().substring(0,(sr.toString().lastIndexOf(","))));
        file1reader.close();
        fileWriter.close();
    }
}
