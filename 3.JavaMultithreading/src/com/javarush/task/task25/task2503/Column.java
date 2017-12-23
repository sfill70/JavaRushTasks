package com.javarush.task.task25.task2503;

import java.util.*;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */


    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    public  String getColumnName(){ return columnName;}

    public  boolean isShown(){return realOrder[ordinal()]!=-1;}

    public  void hide() {
        realOrder[this.ordinal()] = -1;

         /*for (int j = ordinal(); j < realOrder.length; j++) {
             if (j == ordinal()) {
                 realOrder[j] = -1;
             } else {
                 realOrder[j] -= realOrder[j];
             }
         }*/
     }
    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
       List<Column> result = new LinkedList<>();


        /*Map<Integer, Column> map = new TreeMap<>();
        for (int i = 0; i < realOrder.length; i++) {
            map.put(realOrder[i], Column.values()[i]);
        }

        for (Map.Entry<Integer, Column> entry : map.entrySet()){
            if (entry.getKey() != -1)
                result.add(entry.getValue());
        }*/


        for (int i = 0; i <realOrder.length ; i++) {
            result.add(null);
         }
         for (int i = 0; i <realOrder.length ; i++) {
            if(realOrder[i]!=-1){
              result.add( realOrder[i],values()[i]);
            }
        }
        for (int i = 0; i <result.size() ; i++) {
            if(result.get(i)==null){result.remove(i);
            i--;}
        }
        return result;
    }
}
