package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }
    //static int count = 0;
    //  static int subtraction = 0;


    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        int subtraction = 0;
        for (int i = 1; i <= a.length; i++) {
            for (int j = 0; j <= a[1].length; j++) {
                if (i == a.length) {
                    if (j != 0) {
                        if (j == a[1].length) {
                            if (subtraction > 0) {
                                count++;
                            }
                        } else {
                            if (a[i - 1][j] - 0 == 0 && subtraction > 0) {
                                count++;
                                subtraction = a[i - 1][j] - 0;
                            }
                            subtraction = a[i - 1][j] - 0;
                        }

                    } else {
                        subtraction = a[i - 1][j] - 0;
                    }
                } else {
                    if (j != 0) {
                        if (j == a[1].length) {
                            if (subtraction > 0) {
                                count++;
                                // subtraction=0;
                            }
                        } else {
                            if (a[i - 1][j] - a[i][j] == 0 && subtraction > 0) {
                                count++;
                                subtraction = a[i - 1][j] - a[i][j];
                            }
                            subtraction = a[i - 1][j] - a[i][j];
                        }
                    } else {
                        subtraction = a[i - 1][j] - a[i][j];
                    }
                }
            }
        }
        // System.out.println(count);
        return count;
    }
}
