package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
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
        String result = outputStream.toString().replaceAll("[^0-9]", "");//Преобразовываем записанные в наш ByteArray данные в строку
        System.setOut(consoleStream);//Возвращаем все как было
        System.out.println(result);
        System.out.println(result.indexOf("[4-9]"));
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
