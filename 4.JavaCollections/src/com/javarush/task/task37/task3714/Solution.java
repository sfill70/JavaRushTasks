package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        char[] chArray = s.toCharArray();
        int number = 0;

        for (int i = 0; i < chArray.length; i++) {

             if (i == (chArray.length-1)||Solution.OCR(chArray[i])>=Solution.OCR(chArray[i+1]))
                {number=number+Solution.OCR(chArray[i]);}
                else {number=number-Solution.OCR(chArray[i]);}


        }

        return number;
    }

    public static int OCR(char c) {

        int x = 0;
        switch (c) {
            case 'I':
                x = 1;
                break;
            case 'V':
                x = 5;
                break;
            case 'X':
                x = 10;
                break;
            case 'L':
                x = 50;
                break;
            case 'C':
                x = 100;
                break;
            case 'D':
                x = 500;
                break;
                case 'M':
                x = 1000;
                break;
            default:
                break;
        }
return x;

    }
}
