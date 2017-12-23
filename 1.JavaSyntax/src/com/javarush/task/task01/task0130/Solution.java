package com.javarush.task.task01.task0130;

/* 
Наш первый конвертер!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertCelsiumToFahrenheit(40));
    }

    public static double convertCelsiumToFahrenheit(int celsium) {
        //напишите тут ваш код
        double fahrenheit = 9 / 5. * celsium + 32;
       // TC = (TF – 32) * 5/9;
     // double tF=celsium*9/5+32;

        return fahrenheit;
    }
}