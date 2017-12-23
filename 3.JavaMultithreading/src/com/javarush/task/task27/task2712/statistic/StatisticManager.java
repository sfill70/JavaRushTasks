package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by Sfill on 10.08.2017.
 */
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage=new StatisticStorage();
    private Set <Cook> cooks = new HashSet() ;

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }
    public void register(EventDataRow data) { statisticStorage.put(data); }
   public void register(Cook cook) { cooks.add(cook);
   }
   // Метод достанет из хранилища  все данные, относящиеся к отображению рекламы, и посчитает общую прибыль за каждый день.
   public Map<Date, Double> getTotalMoneyPerDay() {
       Map<Date, Double> resultMap = new TreeMap<>(Collections.reverseOrder());

       for (EventDataRow event : statisticStorage.get(EventType.SELECTED_VIDEOS)) {
           Date date = dateWithoutTime(event.getDate());
           VideoSelectedEventDataRow eventData = (VideoSelectedEventDataRow) event;
           if (resultMap.containsKey(date)) {
               resultMap.put(date, resultMap.get(date) + (0.01d * (double) eventData.getAmount()));
           } else {
               resultMap.put(date, (0.01d * (double) eventData.getAmount()));
           }
       }
       return resultMap;
   }
   //метод который из хранилища достанет все данные, относящиеся к работе повара, и посчитает общую продолжительность работы для каждого повара отдельно
   public TreeMap<Date, HashMap<String, Integer>> getCookWorkloadingAgregatedByDay() {
        TreeMap<Date, HashMap<String, Integer>> result = new TreeMap<>();
        for (EventDataRow eventDataRow : statisticStorage.get(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookDataRow = (CookedOrderEventDataRow) eventDataRow;
            GregorianCalendar gDate = new GregorianCalendar();
            gDate.setTime(cookDataRow.getDate());
            for(int i : TIME_FIELDS)
                gDate.clear(i);
            Date date = gDate.getTime();
            HashMap<String, Integer> cooksNameWorkDuration = result.get(date);
            if (cooksNameWorkDuration == null) {
                cooksNameWorkDuration = new HashMap<>();
                result.put(date, cooksNameWorkDuration);
            }
            String cookName = cookDataRow.getCookName();
            Integer cookWorkDuration = cooksNameWorkDuration.get(cookName);
            if (cookWorkDuration == null) cookWorkDuration = Integer.valueOf(0);
            cooksNameWorkDuration.put(cookName, cookWorkDuration + cookDataRow.getTime());
        }
        return result;
    }
    private static final int[] TIME_FIELDS =
            {
                    Calendar.HOUR_OF_DAY,
                    Calendar.HOUR,
                    Calendar.AM_PM,
                    Calendar.MINUTE,
                    Calendar.SECOND,
                    Calendar.MILLISECOND
            };
    /*public TreeMap<Date, TreeMap<String, Integer>> getCookInfo() {
        TreeMap<Date, TreeMap<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow e : statisticStorage.get(EventType.COOKED_ORDER)) {
            Date date = dateWithoutTime(e.getDate());
            CookedOrderEventDataRow cook = (CookedOrderEventDataRow) e;

            int time = cook.getTime();
            if (time == 0) {continue;}
            //if (time % 60 == 0) time = time / 60;
            else { time = (int) Math.ceil((double)time / 60.0);} //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            if (map.containsKey(date)) {
                TreeMap<String, Integer> value = map.get(date);
                if (value.containsKey(cook.getCookName())) {
                    value.put(cook.getCookName(), value.get(cook.getCookName()));
                }
                else value.put(cook.getCookName(), time);
            }
            else {
                TreeMap<String, Integer> value = new TreeMap<>();
                value.put(cook.getCookName(), time);
                map.put(date, value);
            }
        }
        return map;
    }*/


    private Date dateWithoutTime(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<EventDataRow>());
            }
        }
        //метод добавляет объект data в данные карты
        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
               }
        //добавь вспомогательный метод get в класс хранилища, чтобы получить доступ к данным.
        private List<EventDataRow> get(EventType eventType) {
            return storage.get(eventType);
        }
    }


}
