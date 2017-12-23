package com.javarush.task.task22.task2209;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/* 
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args)  throws IOException {
        //...
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String fileName = rd.readLine();
        rd.close();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while (reader.ready()) sb.append(sb.length() == 0 ? reader.readLine() : " " + reader.readLine());
        reader.close();
       // sb.deleteCharAt(0);
        StringBuilder result = getLine(sb.toString().replaceAll((char) 65279+"","").split(" "));
        System.out.println(result.toString());
    }
//c:\14.txt

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return new StringBuilder();
        else if (words.length == 1) return new StringBuilder(words[0]);
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
        while (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                if (sb.length() == 0) {
                    sb.append(list.get(0));
                    list.remove(0);
                }
                if (sb.toString().toLowerCase().charAt(sb.length() - 1) == list.get(i).toLowerCase().charAt(0)) {
                    sb.append(" ").append(list.get(i));
                    list.remove(i);
                    i = -1;
                }
            }
            if (list.size() > 0) {
                list = new ArrayList<>(Arrays.asList(words));
                Collections.shuffle(list);
                sb = new StringBuilder();
            }
            //System.out.println(sb.toString());
        }
        return sb;
    }

}
