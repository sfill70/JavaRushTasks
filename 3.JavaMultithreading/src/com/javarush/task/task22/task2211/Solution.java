package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
           String fileWin= args[0];
            String fileUTF = args [1];
        FileInputStream inStream = new FileInputStream(/*"c:/1.txt"*/     fileWin);
        FileOutputStream inWrite = new FileOutputStream(/*"c:/14.txt"*/    fileUTF);
        byte[] buffer = new byte[ inStream.available()];
        Charset windows1251 = Charset.forName("Windows-1251" );
        Charset UTF = Charset.forName( "UTF-8" );
        //  while (inStream.available() > 0){
        inStream.read(buffer);
        String s = new String(buffer, UTF);
       buffer = s.getBytes(windows1251);
        inWrite.write (buffer/*new String(buffer, "UTF-8").getBytes("Windows-1251")*/);
        // }
        inStream.close();
        inWrite.close();

    }
}
