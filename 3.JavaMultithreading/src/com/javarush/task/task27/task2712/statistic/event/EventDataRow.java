package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Sfill on 10.08.2017.
 */
public interface EventDataRow {
    public EventType getType();
    //вернет дату создания записи
    public Date getDate();
    //вернет время — продолжительность
    public int getTime();
}
