package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sfill on 31.07.2017.
 */
public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static int getOrderCreatingInterval() {
        return ORDER_CREATING_INTERVAL;
    }

    public static void main(String[] args) {


        Locale.setDefault(Locale.ENGLISH);
        Cook cook1 = new Cook("Amigo");
        Cook cook2 = new Cook("Bingo");
        List<Tablet> tablets = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Tablet tablet = new Tablet(i);
            tablets.add(tablet);
            if (i%2 == 0) tablet.addObserver(cook2);
            else tablet.addObserver(cook1);
        }
        Waiter waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        //thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        thread.interrupt();

        /*tablet.addObserver(cook);
        cook.addObserver(waiter);
        tablet.createOrder();*/
        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }
}
/*
Fish
Steak
Soup
exit

Fish
exit

*/