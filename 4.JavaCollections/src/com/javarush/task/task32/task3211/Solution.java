package com.javarush.task.task32.task3211;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/* 
Целостность информации
*/

public class Solution {
       public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(byteArrayOutputStream.toByteArray());

        //Конвертация в hex строку посчитанного md5 значения из ByteArrayOutputStream
        byte[] digist = messageDigest.digest();
        // 1 способ
        String md5HexString = DatatypeConverter.printHexBinary(digist); //Буквы будут заглавные

        // 2 способ
        /* StringBuilder myHash = new StringBuilder();
        for(byte b : digist) {
            myHash.append(String.format("%02x", b)); }
            String md5HexString=myHash.toString();*/

        // 3 способ
        /*BigInteger tmp = new BigInteger(1, digist);
        String md5HexString = tmp.toString(16);
        while( md5HexString.length() < 32 ) //Добивка 0 до длинны в 16 байт
            md5HexString = "0" + md5HexString;*/

        return  md5HexString.equalsIgnoreCase(md5);
// решение в 3 строки
        /*MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger i = new BigInteger(1, messageDigest.digest(byteArrayOutputStream.toByteArray()));
        return String.format("%032x", i).equals(md5);*/
    }

}
