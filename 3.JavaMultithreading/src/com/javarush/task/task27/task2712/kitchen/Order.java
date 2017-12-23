package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sfill on 31.07.2017.
 */
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet)  throws IOException {
        this.tablet = tablet;
        initDishes();
        //System.out.println( this.toString());
    }


    protected void initDishes() throws IOException{
        dishes= ConsoleHelper.getAllDishesForOrder();
    }
    public int getTotalCookingTime(){
        int totalCookingTime=0;
        if (!dishes.isEmpty()){
            for (Dish dish:dishes) {
                totalCookingTime+=dish.getDuration();
            }
        }
        return totalCookingTime;
    }
    public boolean isEmpty(){return dishes.isEmpty();}

    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()){
            return "";
        }
        else {return   "Your order: " + dishes.toString() +" of "+  tablet.toString();}
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
