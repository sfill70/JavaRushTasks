package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        int n = number;
        List <Integer>list=new ArrayList<>();
        while (n>2){
            if (n%3<=1){
                list.add(n%3);
                n=n/3;
              //  System.out.println(n);
            }
            else {
                list.add(n%3);
                n=(n/3)+1;
             //   System.out.println(n);
            }

        }
        if (n==2){
            list.add(2);
            list.add(1);
        }
        if (n==1){
            list.add(1);
        }
        System.out.print(number+"=");
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i)==1){
                System.out.print("+"+ (int)Math.pow(3,i));
            }
            if (list.get(i)==2){
                System.out.print("-"+ (int)Math.pow(3,i));
            }
        }
       // System.out.println(list);
        //напишите тут ваш код
    }
}