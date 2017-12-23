package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int num=0;
        double sum=0;
        int count=0;
        while (true){
            line = reader.readLine();
            num = Integer.parseInt(line);
            if (num != -1){
                sum+=num;
                count++;
            }
            else {
                System.out.println(sum/count);
                break;
            }
        }


        //напишите тут ваш код
    }
}

