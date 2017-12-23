package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;//запоминаем настоящий PrintStream в специальную переменную
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//Создаем динамический массив
        PrintStream stream = new PrintStream(outputStream);//создаем адаптер к классу PrintStream
        System.setOut(stream);//Устанавливаем его как текущий System.out
        testString.printSomething(); //вызываем метод который передает данные с консоли.
        String result = outputStream.toString();//Преобразовываем записанные в наш ByteArray данные в строку
        System.setOut(consoleStream); //Возвращаем все как было
        //System.out.println(result);
        int a = Integer.parseInt((result.split("[-=+*]")[0]).replaceAll(" ","").trim());
        int b = Integer.parseInt((result.split("[-=+*]")[1]).trim());
        int c=0;
        if(result.contains("+")){c=a+b;}
        else if(result.contains("-")){c=a-b;}
        else if(result.contains("*")){c=a*b;}

        result=result.replaceAll("\\r\\n","")+c;
        //result=result+c;

        System.out.println(result);

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }

    }
}

