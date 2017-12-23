package com.javarush.task.task30.task3002;



/*
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62
    }

    public static String convertToDecimalSystem(String s) {

      s=s.toLowerCase();
        int n=0;

        if (s.charAt(0)=='0'){
            if(s.charAt(1) =='x'){
                 n = Integer.parseInt(s.substring(2),16);
             //   System.out.println(s.substring(2));
            }
            if(s.charAt(1) =='b'){
                 n = Integer.parseInt(s.substring(2),2);
             //   System.out.println(s.substring(2));
            }
            if(Character.isDigit(s.charAt(1))){
                n = Integer.parseInt(s,8);
             //   System.out.println(s.substring(1));
            }
        }
        else if (Character.isDigit(s.charAt(0))){
             n = Integer.parseInt(s,10);
        }





        //напишите тут ваш код
        return  Integer.toString(n,10);

       /* String result;

        if (s.startsWith("0x")) {
            result = String.valueOf(Integer.parseInt(s.substring(2, s.length()), 16));
        }
        else if (s.startsWith("0b")) {
            result = String.valueOf(Integer.parseInt(s.substring(2, s.length()), 2));
        }
        else if (s.startsWith("0")) {
            result = String.valueOf(Integer.parseInt(s.substring(1, s.length()), 8));
        }
        else {
            result = s;
        }

        return result;
   */
    }
}
