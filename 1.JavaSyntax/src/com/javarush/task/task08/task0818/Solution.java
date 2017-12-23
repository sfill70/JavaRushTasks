package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Ivanov", 600);
        map.put("Sidorov", 700);
        map.put("Petrov", 500);
        map.put("Sergeev", 50000);
        map.put("Avdeev", 50);
        map.put("Hrenov", 550);
        map.put("Viktorov", 800);
        map.put("Olegov", 400);
        map.put("Igorev", 900);
        map.put("Romanov", 300);
        return map;

        //напишите тут ваш код
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            Integer d =pair.getValue();
            if ( d<500)
            { iterator.remove();   }

        }
        //напишите тут ваш код
    }

    public static void main(String[] args) {

       /* HashMap<String, Integer> map2 = new HashMap<String, Integer>();
        map2=createMap();
        Iterator<Map.Entry<String, Integer>> iterator = map2.entrySet().iterator();
        while (iterator.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator.next();
            System.out.println(pair);

        }

        System.out.println();
         removeItemFromMap(map2);
        Iterator<Map.Entry<String, Integer>> iterator2 = map2.entrySet().iterator();
        while (iterator2.hasNext())
        {
            //получение «пары» элементов
            Map.Entry<String, Integer> pair = iterator2.next();
            System.out.println(pair);

        }*/

    }
}