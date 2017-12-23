package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Sfill on 01.08.2017.
 */
public class Cook extends Observable implements Observer {
    public String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    //Observable o - объект Tablet, Object arg - объект Order.
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),name,order.getTotalCookingTime()*60,order.getDishes()));
        StatisticManager.getInstance().register (this);
        setChanged();
        notifyObservers(arg);
        }

    @Override
    public String toString() {
        return  name ;
    }
}
