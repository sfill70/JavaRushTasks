package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

/**
 * Created by Sfill on 10.01.2018.
 */
public interface StorageStrategy {
    //должен вернуть true, если хранилище содержит переданный ключ.
    boolean containsKey(Long key);

    //должен вернуть true, если хранилище содержит переданное значение.
    boolean containsValue(String value);

    //void put(Long key, String value)
    void put(Long key, String value);

    //вернуть ключ для переданного значения
    Long getKey(String value);

    //вернуть значение для переданного ключа
    String getValue(Long key);

    /*public HashMap<Long, String> getData();*/
}
