package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sfill on 01.08.2017.
 */
public class AdvertisementStorage {

    final private List <Advertisement> videos= new ArrayList();

    private static AdvertisementStorage storage = new AdvertisementStorage();

    public static AdvertisementStorage getInstance() {
        return storage;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        add(new Advertisement(someContent, "Fourth Video", 400, 1, 5 * 60));
        add(new Advertisement(someContent, "Пятое Video", 600, 6, 7 * 60));
    }
    public List <Advertisement> list() {return videos;}

    public void add(Advertisement advertisement){ videos.add(advertisement);}
}
