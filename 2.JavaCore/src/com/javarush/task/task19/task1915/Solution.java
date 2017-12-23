package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        reader.close();

        PrintStream consoleStream = System.out;//запоминаем настоящий PrintStream в специальную переменную
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//Создаем динамический массив
        PrintStream stream = new PrintStream(outputStream);//создаем адаптер к классу PrintStream
        System.setOut(stream);//Устанавливаем его как текущий System.out
        testString.printSomething(); //вызываем метод который передает данные с консоли.
        //String result = outputStream.toString();//Преобразовываем записанные в наш ByteArray данные в строку
        byte [] array = outputStream.toByteArray();
        System.setOut(consoleStream);//Возвращаем все как было

        FileOutputStream outputStream1 =new FileOutputStream(file1);
        outputStream1.write (array);
        outputStream1.close();
        /*FileWriter fileWriter=new FileWriter(file1);
        fileWriter.write(result);
        fileWriter.close();*/
        System.out.println(outputStream.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

