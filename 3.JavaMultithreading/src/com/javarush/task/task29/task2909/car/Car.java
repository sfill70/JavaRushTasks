package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;
    static public final int MAX_TRUCK_SPEED = 80;
    static public final int MAX_SEDAN_SPEED = 120;
    static public final int MAX_CABRIOLET_SPEED = 90;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0) {
            throw new Exception();
        }
        fuel += numberOfLiters;
    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date,SummerStart,SummerEnd)==true)
        {consumption=getSummerConsumption(length);}
        else
            {consumption=getWinterConsumption(length);}
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {

        if (canPassengersBeTransferred())
        { return numberOfPassengers;}
        else {return 0;}
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        fastenDriverBelt();
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
           // fastenDriverBelt();
        } /*else {
            fastenDriverBelt();
        }*/
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }
    public abstract int getMaxSpeed();
    /*public int getMaxSpeed() {
        if (type == TRUCK)
            return MAX_TRUCK_SPEED;
        if (type == SEDAN)
            return MAX_SEDAN_SPEED;

        return MAX_CABRIOLET_SPEED;
    }*/

   public static Car create(int type, int numberOfPassengers){
        /*Car car=new Car(type,numberOfPassengers);
        if (type==2){car= new Cabriolet(numberOfPassengers);}
        if (type==1){car= new Sedan(numberOfPassengers);}
        if (type==0) {car= new Truck(numberOfPassengers);}
        return car;*/

         switch (type) {
           case CABRIOLET:
               return new Cabriolet(numberOfPassengers);
           case SEDAN:
               return new Sedan(numberOfPassengers);
           case TRUCK:
               return new Truck(numberOfPassengers);
           default:
               return null /*new Car(type, numberOfPassengers)*/ ;
       }

    }

    public boolean isSummer(Date date , Date summerStart, Date summerEnd) {
       return (date.after(summerStart) && date.before(summerEnd));
       }

    public double getWinterConsumption(int length) {return length*winterFuelConsumption+winterWarmingUp;}
    public double getSummerConsumption(int length) {return length*summerFuelConsumption;}

    private boolean canPassengersBeTransferred(){
        return (driverAvailable && fuel>0);

   }


}