package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)

1. Метод veryComplexMethod класса veryComplexClass не должен использовать ключевое слово throw.
2. Метод veryComplexMethod класса veryComplexClass должен бросать исключение.
3. Брошенное исключение НЕ должно быть исключением времени выполнения(RuntimeException).
4. Метод veryComplexMethod не должен быть приватным.
*/

import java.io.FileReader;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        FileReader fileReader = new FileReader("unknown.file.txt");
        //напишите тут ваш код
    }

    public static void main(String[] args) {

    }
}
