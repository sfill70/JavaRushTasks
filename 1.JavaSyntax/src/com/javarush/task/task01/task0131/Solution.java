package com.javarush.task.task01.task0131;

/* 
Полнометражная картина
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getMetreFromCentimetre(273));
    }

    public static int getMetreFromCentimetre(int centimetre) {
        int liht= centimetre/100;
       return liht;
        //напишите тут ваш код
    }
}