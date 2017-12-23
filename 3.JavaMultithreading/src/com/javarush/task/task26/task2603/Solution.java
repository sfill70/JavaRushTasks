package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class  CustomizedComparator <T> implements Comparator <T>{
        private Comparator <T>[] comparators;
       public CustomizedComparator (Comparator<T>...comparator ){
           this.comparators=comparator;
       }
        @Override
        public int compare(T obj1, T obj2){
           int result=0;
            for (Comparator <T> comp: comparators) {
                result=comp.compare(obj1,obj2);
                if (result!=0) {
                    break;
               }
            }
           return result;}


    }

    public static void main(String[] args) {

    }
}
