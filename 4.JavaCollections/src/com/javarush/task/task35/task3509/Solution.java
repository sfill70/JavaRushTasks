package com.javarush.task.task35.task3509;

import java.util.*;


/* 
Collections & Generics

Реализуй вспомогательныe методы в классе Solution, которые должны создавать соответствующую коллекцию
 и помещать туда переданные объекты.
Методы newArrayList, newHashSet параметризируй типом T.
Метод newHashMap параметризируй типами К(ключ) и V(значение). Аргументы метода newHashMap должны
 принимать списки, в которых содержатся наследники типов K и V.
Возвращаемые коллекции должны быть такого же типа, что и переданные в метод объекты.
Подсказка: в методе newHashMap нужно проверить чтобы списки ключей и значений совпадали по размерам
, в противном случае кинь IllegalArgumentException.
Требования:
1. Метод newArrayList должен быть параметризован типом Т.
2. Метод newArrayList должен возвращать ArrayList, который содержит переданные в метод объекты.
3. Метод newHashSet должен быть параметризован типом Т.
4. Метод newHashSet должен возвращать HashSet, который содержит переданные в метод объекты.
5. Метод newHashMap должен быть параметризован типом K и V.
6. Метод newHashMap должен возвращать HashMap, который содержит переданные в метод ключи и значения.
7. Метод newHashMap должен кидать IllegalArgumentException, если списки ключей и значений не совпадают по размеру.
*/
public class Solution {

    public static void main(String[] args) {

        ArrayList <String> stArray=newArrayList("Метод", "newHashMap", "должен", "быть", "параметризован");
        ArrayList<Integer> intArray = newArrayList(1,2,3,4,5);


        HashMap <Integer, String> map = newHashMap(intArray,stArray);
        HashMap <? extends Object, ? extends Object>  map2 = newHashMap(stArray,intArray);
        map.forEach((k, v) -> System.out.println(k+": "+v));
        map2.forEach((k, v) -> System.out.println(k+": "+v));

        for (Map.Entry<Integer,String> pair : map.entrySet()) {
            System.out.println( pair.getKey() +": "+ pair.getValue());
        }

        for (Map.Entry  pair : map2.entrySet()) {
            System.out.println( pair.getKey() +": "+ pair.getValue());
        }

    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код


        ArrayList <T> list= new ArrayList<T>(Arrays.asList( elements)); // можно создать вот так.

        ArrayList <T> array = new ArrayList<T>();
        for (T el : elements) {
          array.add(el);
        }
        return array;
    }

    public static <T> HashSet <T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet <T> set = new HashSet<T>();
        for (T el : elements) {
           set.add(el);
        }
        return set;
    }

    public static <K, V> HashMap <K , V > newHashMap(List <? extends K> keys, List <? extends V> values) {
        //напишите тут ваш код
        HashMap<K, V> res = new HashMap<>();
        if (keys.size() != values.size())
        {
            System.out.println("size List's !=");
            throw new IllegalArgumentException();
           }
else {

        for (int i = 0; i < keys.size(); i++) {
            res.put(keys.get(i), values.get(i));}
        }

        return res;
    }

    public void   newArrayList2(List elements) {
        //напишите тут ваш код


        ArrayList list= new ArrayList(Arrays.asList( elements)); // можно создать вот так.

        ArrayList array = new ArrayList();
        for (Object el : elements) {
            System.out.println(el);
            array.add(el);
        }

    }

}
