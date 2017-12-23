package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList <String>list = initList(10);
        analizator(list);
    }
    
    public static void analizator(ArrayList<String> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).length() > list.get(i).length()) {
                System.out.println(i);
                break;
            }
        }
    }

    public static ArrayList<String> initList(int n) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList list = new ArrayList();
        for (int i = 0; i < n; i++) {
            list.add(reader.readLine());
        }
        return list;
}
}

