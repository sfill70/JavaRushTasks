package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

/**
 * Created by Sfill on 10.01.2018.
 */
public class Shortener {
    //Это поле будет отвечать за последнее значение идентификатора, которое было использовано при добавлении новой строки в хранилище.
    private Long lastId;

    //будет храниться стратегия хранения данных
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.lastId = 0L;
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        } else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
