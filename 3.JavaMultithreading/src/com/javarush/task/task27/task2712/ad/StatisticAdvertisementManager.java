package com.javarush.task.task27.task2712.ad;


import java.util.*;

/**
 * Created by Sfill on 17.08.2017.
 */
public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
    private AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public List<Advertisement> getActiveAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        if (!storage.list().isEmpty()) {
            for (Advertisement advertisement : storage.list()) {
                if (advertisement.getHits() > 0)
                    result.add(advertisement);
            }
        }
        return result;
    }

    public List<Advertisement> getNonActiveAdvertisements() {
        List<Advertisement> result = new ArrayList<>();
        if (!storage.list().isEmpty()){
        for (Advertisement advertisement : storage.list()) {
            if (advertisement.getHits() == 0)
                result.add(advertisement);
        }}
        return result;
    }
}