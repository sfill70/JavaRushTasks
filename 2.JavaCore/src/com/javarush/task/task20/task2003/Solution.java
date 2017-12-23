
package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        InputStream inputStream = new FileInputStream(fileName);
        //Solution loadedObject=new Solution();
        try {
            load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        inputStream.close();
        //implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {
        Properties prop = new Properties();
        prop.putAll(properties);
        prop.store(outputStream,null); // метод save -- ОСУЖДАЕМЫЙ лучше store.
        //implement this method - реализуйте этот метод
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop=new Properties();
        prop.load(inputStream);
        prop.forEach((k, v) ->{properties.put(k.toString(),v.toString());});
        //implement this method - реализуйте этот метод
    }

    public static void main(String[] args) throws IOException {
        new Solution().fillInPropertiesMap();
        OutputStream outputStream=new FileOutputStream("c:/2.txt") ;
        try {
            new Solution().save(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
