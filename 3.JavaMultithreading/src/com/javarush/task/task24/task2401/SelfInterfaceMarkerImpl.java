package com.javarush.task.task24.task2401;

/**
 * Created by Sfill on 25.05.2017.
 */
public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
    public void print(){
        System.out.println("SelfInterfaceMarkerImpl");
    }
    public void getClas(){
        System.out.println(new SelfInterfaceMarkerImpl().getClass());}
}
