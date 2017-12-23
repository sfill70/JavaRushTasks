package com.javarush.task.task29.task2909.car;

/**
 * Created by Sfill on 13.06.2017.
 */
public class Truck extends Car{

    public Truck( int numberOfPassengers) {
        super(0, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_TRUCK_SPEED;
    }
}
