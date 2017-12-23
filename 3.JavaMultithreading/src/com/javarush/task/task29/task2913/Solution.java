package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        StringBuilder builder = new StringBuilder();
        if (a > b) {
            for (int i = a; i >=b ; i--) {
                builder.append(i).append(" ");
            }
         //   return a + " " + recursion(a - 1, b);
        } else {
            if (a == b) {
                builder.append(a);
               // return Integer.toString(a);
            }
            for (int i = a; i <=b ; i++) {
                builder.append(i).append(" ");
            }
           // return a + " " + recursion(a + 1, b);
        }
        return builder.toString().trim();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt() % 1_000;
        numberB = random.nextInt() % 10_000;
        //System.out.println( random.nextInt());
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}