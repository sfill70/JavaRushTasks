package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }

    public static String getPartOfString  (String string) throws TooShortStringException{
        if (string == null)
        {throw new TooShortStringException();}
        if(!string.contains("\t")){throw new TooShortStringException();}
        if(!string.substring(string.indexOf("\t")+1).contains("\t")){throw new TooShortStringException();}
        String result = new String();


        try {
            String s[] = string.split("\t");
            result=s[1];
        } catch (Exception e) {
            throw new  TooShortStringException();
        }

        return result;
    }

    public static class TooShortStringException extends Exception{
    }


}
