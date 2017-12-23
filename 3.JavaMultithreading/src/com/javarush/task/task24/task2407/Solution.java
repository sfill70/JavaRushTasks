package com.javarush.task.task24.task2407;

import java.util.List;

/* 
Реализация интерфейса используя локальный класс
*/
public class Solution {
    public static void main(String[] args) {
        List<Pet> pet = Util.getPets();
        List<Sayable> pets = Util.convertPetToSayable(pet);
        //System.out.println(pets.get(2).getClass().getSimpleName());
      //  System.out.println(pet.get(3).toSayable(2).getClass());
        Util.printDialog(pets);
    }
}
