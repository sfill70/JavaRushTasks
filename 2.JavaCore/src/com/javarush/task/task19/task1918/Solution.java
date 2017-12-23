package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        String tag = args[0];

   //     String pattern1 = ("<(" + tag + ").*?>") + (".*?") + ("</(" + (tag) + ")>{1}");  //(?!"<")(?!a)
        // String pattern1 = ("<"+tag+".*?>")+(".*?")+("</"+tag+">(?=(<("+tag+").*?>))");  // РАБОТАЕТ(?!"<")  (?!a)
        // String pattern = ("<"+tag+".*?>")+(".*?")+("</"+tag+">(?=(\\w*\\W*<"+tag+".*?>)|(.*$))");  // РАБОТАЕТ ЕЩЕ ЛУЧШЕ
        //  String pattern = "(<"+tag+".*?>).*?</"+tag+">(?=(\\W*\\w*<"+tag+".*?>)|(\\w*\\W*$))";  //
        String pattern = "(<" + tag + ".*?>)|(</" + tag + ">)";  //

        Pattern p = Pattern.compile(pattern);
      //  Pattern p1 = Pattern.compile(pattern1);
        String tmp = new String();
        StringBuilder txt = new StringBuilder();


        BufferedReader file1reader = new BufferedReader(new FileReader(/*"c:/7.txt"*/fileName));
        while ((tmp = file1reader.readLine()) != null) {
            txt.append(tmp.replaceAll("[\\r\\n]", ""));
        }
        file1reader.close();
        ArrayList<String> list = new ArrayList<>();
        Matcher m = p.matcher(txt.toString());
        int count = 0;
        int count2 = 0;
        int start = 0;
        int end = 0;
        while (m.find()) {
            end = m.start();
            if (count != 0 && end != start) {
                list.add(txt.toString().substring(start, end));
            }
            if (m.group().contains("<" + tag)) {
                count++;
            } else {
                count--;
            }
            list.add(m.group());
            if (count == 0) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i)/*+"-"+i*/);
                }
                System.out.println();
                int point = 0;
                int point2 = 0;
                if (list.size() > 3) {
                    for (int i = 1; i < list.size(); i++) {
                        if (list.get(i).contains("<" + tag)) {
                            if (count2 == 0) {
                                point = i;
                            }
                            count2++;
                          /*System.out.println(list.get(i)+count2+"-"+i+"-"+point);*/
                        }
                        if ((list.get(i).contains("</" + tag)) && count2 > 0) {
                            count2--;
                            point2 = i;/*System.out.println(list.get(i)+count2+"-"+i+"-"+point2);*/
                            if (count2 == 0) {
                                for (int j = point; j <= point2; j++) {
                                    System.out.print(list.get(j));
                                    i = point;
                                }
                                System.out.println();
                            }

                        }
                    }
                }
                list.clear();
            }
            start = m.end();
        }
    }
}

/*import java.io.*;
import java.util.ArrayList;
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        FileReader reader = new FileReader(fileName);
        ArrayList<Character> chars = new ArrayList<>();
        int symbol;
        while ((symbol = reader.read()) != -1) {
            chars.add((char) symbol);
        }
        char[] tagChar = args[0].toCharArray();
        int x = 0;    //x служит триггером для вывода. если нашли span, то x++, если нашли /span, то x--. Если при этом x == 0, то вывод, если при этом x != 0, то x--;
        int pointer = 0;
        for (int i = 0; i < chars.size(); i++) {    // главный цикл
//            System.out.print(chars.get(i) + "!");
            if (chars.get(i) == tagChar[0]) {    // нашли нужный символ
                int comparison = 1;
                for (int j = 1; j < tagChar.length; j++) {
                    if (chars.get(i + j) == tagChar[j]) comparison++;
                }
                if (comparison == tagChar.length) {    // нашли тэг
                    if (chars.get(i - 1) == '<') {    // если тэг открывающий
                        if (x == 0) pointer = i;    // если тэг первый и открывающий, то ставим точку
                        x++;
                    }
                    if (chars.get(i - 1) == '/' && (x != 0)) {
                        x--;
                        if (x == 0) {    // если уровень совпадает
                            for (int j = pointer - 1; j < i + tagChar.length + 1; j++) {
                                if (chars.get(j) != 10) {
                                    System.out.print(chars.get(j));
                                }
                            }
                            System.out.println();
                            i = pointer + tagChar.length;
                        }
                    }
                }
            }
        }

        reader.close();
    }
}*/