package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by Sfill on 31.07.2017.
 */
public class Tablet extends Observable {
    final int number;

    private static Logger logger =  Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder(){

        try {
            Order order  = new Order(this);
            intOrder(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Console is unavailable.");
        }


        // return order;
    }
   public  void createTestOrder(){

       try {
           final TestOrder order  = new TestOrder(this);
           intOrder(order);
       } catch (IOException e) {
           logger.log(Level.SEVERE,"Console is unavailable.");
       }

   }

    private void intOrder(Order order) {
        try {
                    ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()) {
                setChanged ();
                notifyObservers(order);

                new AdvertisementManager(order.getTotalCookingTime()*60).processVideos();

            }

        }
        catch (NoVideoAvailableException e) {
            logger.log(Level.INFO,"No video is available for the order " + order);
        }
    }


    @Override
    public String toString() {
        return /*String.format("Tablet{number=%d}",number);*/
        "Tablet{" + "number=" + number +'}';
    }
}
