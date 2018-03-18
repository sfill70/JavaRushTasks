package com.javarush.task.task33.task3310.strategy;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sfill on 11.01.2018.
 */
public class OurHashMapStorageStrategy implements StorageStrategy {

  
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    final int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    int indexFor(int hash, int length) {
        return hash & (length-1);
    }


   /* Entry getEntry(Long key) {
        if (table.length != 0) {
            for (Entry entry : table) {
                if (entry.getKey() == key) {
                    return entry;
                } else {
                    return null;
                }
            }
        }
        return null;
    }*/

     Entry getEntry(Long key) {
         if (size == 0)
         {  return null;}
         int hash = (key == null) ? 0 : hash(key);
        for (Entry e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    void resize(int newCapacity){
        Entry[] oldTab = table;
       // int oldCap = (oldTab == null) ? 0 : oldTab.length;
        Entry [] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        Entry[] oldTab = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < oldTab.length; j++) {
            Entry  e = oldTab[j];
            if (e != null) {
                oldTab[j] = null;
                do {
                    Entry  next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry (hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }
    void createEntry(int hash, Long key, String value, int bucketIndex){
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, e);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
        { return false;}
        Entry[] tab = table;
        for (int i = 0; i < tab.length ; i++){
            for (Entry e = tab[i] ; e != null ; e = e.next)
            { if (value.equals(e.value))
                { return true;}
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key),table.length));
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0L;

        for (Entry table : table) {
            for (Entry e = table; e != null; e = e.next)
                if (value.equals(e.value))
                    return table.getKey();
        }
        return null;

    }

    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }
}
