package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
     //   File file = new File("c:/data.properties");
        Properties properties = new Properties();

        try {
            FileInputStream inStream = new FileInputStream(fileName);
            if (fileName.toLowerCase().endsWith(".xml")){
                properties.loadFromXML(inStream);
            }
            else {properties.load(inStream);}
            inStream.close();
        } catch (IOException e) {

        }

        return properties;
    }
}
