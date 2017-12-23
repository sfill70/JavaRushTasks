package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {

        Solution [] array = new Solution[2];
       Solution solution = new Solution();
       solution.innerClasses[0]= solution.new InnerClass(); //один способ создание объекта  innerClasses
       solution.innerClasses[1]= solution.new InnerClass();
        Solution solution1 = new Solution();
        solution1.innerClasses[0]= new Solution().new InnerClass(); //второй способ создание объекта  innerClasses
        solution1.innerClasses[1]= new Solution().new InnerClass();

        array[0]=solution;
        array[1]=solution1;
        return array;
    }

    public static void main(String[] args) {

    }
}
