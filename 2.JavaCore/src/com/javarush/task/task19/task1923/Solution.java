package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName1 = args[0];
        String fileName2 = args[1];

        String pattern = "[\\p{Graph}]*\\d+[\\p{Graph}]*";
      //  String pattern = "\\b\\d{1}\\d{1}\\b";
       // String pattern = "\\b[0-9]+[\\w&&[^0-9]]+\\w*\\b|\\b[\\w&&[^0-9]]+[0-9]+\\w*\\b"; //ВОТ ЭТО
      //  String pattern = "[0-9]+[\\w&&[^0-9]]+\\w*|[\\w&&[^0-9]]+[0-9]+\\w*";

        Pattern p = Pattern.compile(pattern);

        BufferedReader file1reader = new BufferedReader(new FileReader(fileName1));
       BufferedWriter fileWriter = new BufferedWriter (new FileWriter(fileName2));
      //  FileWriter fileWriter = new FileWriter(args[1]);
        String tmp= new String();
        StringBuilder sr=new StringBuilder();
        while ((tmp=file1reader.readLine()) != null){
          //  tmp=file1reader.readLine();
           /*String [] array = tmp.split(" ");
            for (int i = 0; i <array.length ; i++) {
                char[] ch = array[i].toCharArray();
                for (char c : ch) {
                    if (Character.isDigit(c)) {
                        sr.append(array[i]);
                        sr.append(" ");
                        break;
                    }
                }
            }*/


             Matcher m = p.matcher(tmp);
            while (m.find()){
              //  sr.append(tmp.substring(m.start(), m.end()) + " ");
                fileWriter.write(tmp.substring(m.start(), m.end()) + " ");
               // System.out.print(tmp.substring(m.start(), m.end()) + " ");
             //   System.out.println(m.group());
                //К ДЕЛУ НЕ ОТНОСИТЬСЯ
                String chen=m.group();
                System.out.println(chen);
                tmp=tmp.replaceAll(chen,"tratata");
                System.out.println(tmp);
            }
            System.out.println(tmp);

        }
       System.out.println(sr.toString());

      //  fileWriter.write(sr.toString().trim());
        fileWriter.close();
        file1reader.close();

        String pattern2 ="//d*";
        Pattern p2 = Pattern.compile(pattern2);
        boolean bo;
        bo=("12"==pattern2);
        System.out.println(bo+" "+pattern2);

    }
}
