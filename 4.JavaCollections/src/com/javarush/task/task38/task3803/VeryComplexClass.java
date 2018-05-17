package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object ch = new Character('*');
        System.out.println((Byte)ch);
    }

    public void methodThrowsNullPointerException() {
        int[] nNulArray = new int[5];
        nNulArray = null;
        int i = nNulArray.length;
    }

    public static void main(String[] args) {

    }
}
