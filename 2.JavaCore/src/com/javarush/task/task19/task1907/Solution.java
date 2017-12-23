package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        //  String pattern = "(world)";
        String pattern = "\\b(world)\\b";
        Pattern p = Pattern.compile(pattern);
       // String st=new String();

        FileReader fileReader = new FileReader(fileName);
        StringBuilder st=new StringBuilder();
        int co =0;
        while (fileReader.ready()){
            int data = fileReader.read();
            st.append((char) data);
        }
       /* while ((st = fileReader.readLine()) != null){
            Matcher m = p.matcher(st);
            while(m.find()) {
                co++;
              //  System.out.print(st.substring(m.start()-1, m.end()) + "*");
            }
        }*/
        fileReader.close();
        Matcher m = p.matcher(st.toString());
        while(m.find()) {
            co++;
              System.out.print(st.substring(m.start(), m.end()) + "*");
        }
        System.out.println(co);
    }
}
