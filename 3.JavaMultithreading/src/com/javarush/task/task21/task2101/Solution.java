package com.javarush.task.task21.task2101;

import java.util.Arrays;

/*
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
        System.out.println(Arrays.toString(mask));
        for (byte by:netAddress
             ) {
            System.out.println(Integer.toBinaryString(by));
            System.out.println(Byte.toUnsignedInt(by));

        }
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[]netAdd=new byte[4];
        for (byte i = 0; i <4 ; i++) {
             netAdd[i]=(byte) (ip[i]& mask[i]);

        }


        return netAdd;
    }

    public static void print(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
         //   System.out.print(Integer.toString(bytes[i]& 0b11111111, 2));
           // int f = (int) bytes[i]; // получаем значение и приводим его к интегер
        //    if(f < 0) f += 256;  // если значение меньше 0, то + 256 - тут получается исходник из -64 192
         //   String str = Integer.toString(bytes[i]& 0b11111111, 2); // преобразовываем к двоичному коду
            System.out.printf("%08d", Integer.parseInt(Integer.toString(bytes[i]& 0b11111111, 2)));////СБРОС БИТА ЗНАКА, ЛОГИЧЕСКОЕ ПОБИТОВОЕ "И", ОТРИЦАТЕЛЬНЫЕ БИТЫ от -128 до 0, становятся положительными от 127 до 256,  0b11111111==255
            if (i < bytes.length - 1) System.out.print(" ");
        }
        System.out.println();
    }
    }

