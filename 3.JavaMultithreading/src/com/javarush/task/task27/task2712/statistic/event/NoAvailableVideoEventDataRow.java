package com.javarush.task.task27.task2712.statistic.event;

import java.util.Date;

/**
 * Created by Sfill on 13.08.2017.
 */
public class NoAvailableVideoEventDataRow implements EventDataRow {

    private int totalDuration; //время приготовления заказа в секундах
    private Date currentDate;

    public NoAvailableVideoEventDataRow(int totalDuration) {
        this.totalDuration = totalDuration;
        this.currentDate=new Date();
    }

    @Override
    public EventType getType() {
        return EventType.NO_AVAILABLE_VIDEO;
    }

    @Override
    public Date getDate() {
        return currentDate;
    }

    @Override
    public int getTime() {
        return totalDuration;
    }
}
