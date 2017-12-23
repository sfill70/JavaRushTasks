package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        if (string == null)
        {throw new TooShortStringException();}
        String result = new String();
        /*int tmp=0;
        tmp=string.indexOf(" ", tmp)+1;*/

        try {
            /*for (int i=0;i<4;i++) {
                result+=(string.substring(tmp, tmp=string.indexOf(" ", tmp+1)));}*/
            String s[] = string.split(" ");
            result = s[1] + " " + s[2] + " " + s[3] + " " +  s[4];

        } catch (Exception e) {
            throw new TooShortStringException();
        }

        return result;
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
