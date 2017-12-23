package com.javarush.task.task26.task2601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {


    public static void main(String[] args) {

        /*Integer[]ar=new Integer[]{13, 8, 15, 5, 17};
        sort(ar);
        System.out.println(Arrays.toString(ar));*/

    }

    public static Integer[] sort(Integer[] array) {
        Arrays.sort(array);
        final double mediana;
        if (array.length % 2 == 0)
            mediana = ((double) array[array.length / 2 - 1] + (double) array[array.length / 2]) / 2;
        else
            mediana = array[array.length / 2];

        Comparator<Integer> compare = new Comparator<Integer>() {

            public int compare(Integer i1, Integer i2) {
                int result = 0;
                result = Double.compare(Math.abs(mediana - i1), Math.abs(mediana - i2));
                if (result == 0) {
                    result = Integer.compare(i1, i2);
                }
                return result;

            }
        };
        Arrays.sort(array, compare);
        //implement logic here
        return array;
    }
}
