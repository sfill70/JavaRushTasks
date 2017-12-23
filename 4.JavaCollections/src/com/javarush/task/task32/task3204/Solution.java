package com.javarush.task.task32.task3204;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }
//Случаное чисоло в заданном диапазоне
    // int randomDigits = random.ints(48, 57).limit(1).findFirst().getAsInt();

    public static ByteArrayOutputStream getPassword() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte [] array = new byte[8];
        Random random = new Random();
        int ran=0;
        int ranByte=0;
        int countDig=0;
        int countUpCase=0;
        int countLowCase=0;
        for (int i = 0; i <8 ; i++) {
            ran=random.nextInt(3);
            if (i==5&&countDig==0){
                ran=0;
            }
            if (i==6&&countUpCase==0){
                ran=1;
            }
            if (i==7&&countLowCase==0){
                ran=2;
            }
           switch (ran) {
                case 0:
                    ranByte =(int) (Math.random()*10)+48;
                    countDig++;
                    break;
                case 1:
                    ranByte = (int) (Math.random()*26)+65;
                    countUpCase++;
                    break;
                case 2:
                    ranByte = (int) (Math.random()*26)+97;
                   countLowCase++;
                   break;
            }
          //  outputStream.write(ranByte);
            array[i]= (byte) ranByte;
        }
        outputStream.write(array);
        outputStream.close();

        return outputStream;
    }
}