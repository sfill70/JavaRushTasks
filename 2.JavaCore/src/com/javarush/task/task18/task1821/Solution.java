package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import sun.misc.ASCIICaseInsensitiveComparator;
import sun.nio.cs.KOI8_R;
import sun.nio.cs.KOI8_U;
import sun.nio.cs.UnicodeEncoder;

import java.io.*;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.charset.StandardCharsets.*;
import static javafx.scene.input.KeyCode.K;


public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
/*
        // Почемуто этот не прошел ??? наверно из за BufferedReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file *//*"c:/4.txt"*//*)*//*, StandardCharsets.UTF_8*//*));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        char[] buffer = sb.toString().toCharArray();*/

       StringBuilder sb = new StringBuilder();
        FileInputStream in = new FileInputStream(file  /*"c:/1.txt"*/);
        byte bytes[] = new byte[in.available()];
        byte b = 0;
        while (in.available() > 0) {
            sb.append((char) in.read());

        }
        in.close();

       /* byte [] qwer = sb.toString().getBytes ();
        for (int i = 0; i < qwer.length; i++ ) {
            if (qwer[i] != 0)
                System.out.print((char)qwer[i]);
        }*/
        // System.out.println(sb.toString());
        char[] buffer = sb.toString().toCharArray();

        long[] array = new long[127];



        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] < 127) {
                //  System.out.println(buffer[i]);
                array[buffer[i]]++;//=array[i]+1;
            }
        }

        for (int i = 0; i < array.length; i++ ) {
            if (array[i] != 0)
                System.out.println(i+" "+(char) i + " " + array[i]);

        }

    }
}
