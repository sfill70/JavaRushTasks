package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String>list=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i =0;
        while (i<10) {
            String s = reader.readLine();
            list.add(s);
            i++;
        }
        int min=0;
        int max=0;

        for (int j = 1; j <list.size() ; j++) {
            if (list.get(j).length()<list.get(min).length()){
                min=j;
            }
            if (list.get(j).length()>list.get(max).length()){
                max=j;
            }
          //  System.out.println(min+" :" +max);
        }
        if (min<max){
            System.out.println(list.get(min));
        }
        else {
            System.out.println(list.get(max));
        }
        //напишите тут ваш код
    }
}
