package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sfill on 16.08.2017.
 */
public class DirectorTablet {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    //какую сумму заработали на рекламе, сгруппировать по дням
    public void printAdvertisementProfit(){
        double total = 0;
        for (Map.Entry<Date, Double> entry : StatisticManager.getInstance().getTotalMoneyPerDay().entrySet()) {

            double e = entry.getValue();
            ConsoleHelper.writeMessage(String.format("%s - %.2f", simpleDateFormat.format(entry.getKey()), e));
            total += e;
        }
        if (total > 0) ConsoleHelper.writeMessage(String.format("Total - %.2f",total));
    }
    //загрузка (рабочее время) повара, сгруппировать по дням

    public void printCookWorkloading() {
         TreeMap<Date, HashMap<String, Integer>> cookWorkloadingAgregatedByDay = StatisticManager.getInstance().getCookWorkloadingAgregatedByDay();
        if (cookWorkloadingAgregatedByDay.isEmpty()) return;
        NavigableSet<Date> datesRow = cookWorkloadingAgregatedByDay.descendingKeySet();
        for (Date date: datesRow) {
            ConsoleHelper.writeMessage(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(date));
            List<Map.Entry<String, Integer>> cooksNameWorkDuration = new ArrayList(cookWorkloadingAgregatedByDay.get(date).entrySet());
            Collections.sort(cooksNameWorkDuration, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return b.getValue() - a.getValue();
                }
            });
            for (Map.Entry<String, Integer> cookNameWorkDuration: cooksNameWorkDuration) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",
                        cookNameWorkDuration.getKey(),
                        (int)Math.ceil(cookNameWorkDuration.getValue() / 60.0))
                );
            }
        }
    }

    // statisticAdvertisementManager.getActiveVideoSet(activeVideoSet,archivedVideoSet);
    //список активных роликов и оставшееся количество показов по каждому
    public void printActiveVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getActiveAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(String.format("%s - %d",
                    advertisement.getName(),
                    advertisement.getHits()));
        }
    }
    //список неактивных роликов (с оставшемся количеством показов равным нулю
    public void printArchivedVideoSet() {
        List<Advertisement> videoSet = StatisticAdvertisementManager.getInstance().getNonActiveAdvertisements();
        Collections.sort(videoSet, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (Advertisement advertisement : videoSet) {
            ConsoleHelper.writeMessage(advertisement.getName());
        }
    }
}
