package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String advertising ="JavaRush - курсы Java онлайн";
        StringBuilder builder = new StringBuilder();
        PrintStream consoleStream = System.out;             //запоминаем настоящий PrintStream в специальную переменную
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();          //Создаем динамический массив
        //создаем адаптер к классу PrintStream и пререопределяем метод println
        PrintStream stream = new PrintStream(outputStream) {
            int count = 0;
             @Override
            public void println(String x) {
                count++;
                if (count % 2 == 0) {

                        print(x);
                        print(System.lineSeparator());
                        print("JavaRush - курсы Java онлайн");
                        print(System.lineSeparator());

                } else {

                        print(x);
                        print(System.lineSeparator());

                }
            }

        };

        System.setOut(stream);        //Устанавливаем его как текущий System.out
        testString.printSomething();
        System.setOut(consoleStream);   //Возвращаем все как было

        String result = outputStream.toString();
        System.out.println(result);
        System.out.println(outputStream.getClass());

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
