package com.javarush.task.task05.task0514;

/* 
Программист создает человека
*/

public class Solution {
    public static void main(String[] args) {
        Person person = new Person();
        person.initialize("Petr", 18);
        //напишите тут ваш код
    }

    static class Person {
        String name;
        int age;
      //  char sex;

        public void initialize (String name, int age){
           this.name = name;
           this.age = age;
        }
        //напишите тут ваш код
    }
}