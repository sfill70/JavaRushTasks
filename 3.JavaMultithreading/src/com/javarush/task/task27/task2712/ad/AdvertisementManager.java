package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sfill on 01.08.2017.
 */
public class AdvertisementManager {





    AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds; //это время выполнения заказа поваром в секундах

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }



    public void processVideos() {

        if (storage.list().isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();

        }

        List<Advertisement> list = getList();
        if (list.isEmpty())
        { StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();

        }
        Collections.sort(list, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                long am1 = o1.getAmountPerOneDisplaying(), am2 = o2.getAmountPerOneDisplaying();
                return am2 == am1 ? Long.compare(1000 * am1 / o1.getDuration(), 1000 * am2 / o2.getDuration()) : Long.compare(am2, am1);
            }
        });

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(list, price(list), duration(list)));

        for (Advertisement advert : list) {
            long amount = advert.getAmountPerOneDisplaying();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d", advert.getName(), amount,
                    1000 * amount / advert.getDuration()));

                      advert.revalidate();

        }
     }

    private void  fillingList( List<List<Advertisement>> set){
        int amountOfElements = storage.list().size();
        for (int i = 1; i <= amountOfElements; i++) {
            int[] a = new int[i];
            fillingListRecursion2(set, a, i, amountOfElements, 0, 0);
        }
    }
    private void fillingListRecursion2(List<List<Advertisement>> list, int[] a, int k, int n, int pos, int maxUsed) {
        if (pos == k) {
            // System.out.println(Arrays.toString(a));
            //Здесь по сформированному массиву элементов N по М "Без повторений" фомируется лист, n - количество элементов , к - длинна сочетания
            List<Advertisement> advertisements = new ArrayList<>();
            int time = 0;
            int countHits = 0;
            for (int in : a) {
                advertisements.add(storage.list().get(in - 1));
            }
            list.add(advertisements);
        } else {
            for (int i = maxUsed + 1; i <= n; i++) {
                a[pos] = i;
                fillingListRecursion2(list, a, k, n, pos + 1, i);
            }
        }
    }

    //метод, который выбирает лучший лист
    private List<Advertisement> getList() {
        List<List<Advertisement>> list = new ArrayList<>();
        fillingList(list);
        Iterator<List<Advertisement>> it = list.iterator();
        //сначала отсеиваем листы, содержащие Advertisement с hits <=0, либо слишком длинные по времени
        while (it.hasNext()) {
            List<Advertisement> next = it.next();
            int time = 0;
            for (Advertisement ad : next) {
                if (ad.getHits() <= 0) {
                    it.remove();
                    break;
                }
                time += ad.getDuration();
                if (time > timeSeconds) {
                    it.remove();
                    break;
                }
            }
        }
        //пункты задания 1 и 4
        return Collections.max(list, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> list1, List<Advertisement> list2) {
                long price1 = price(list1), price2 = price(list2);
                if (price1 != price2) return Long.compare(price1, price2);
                int duration1 = duration(list1), duration2 = duration(list2);
                if (duration1 != duration2) return Integer.compare(duration1, duration2);
                int size2 = list2.size(), size1 = list1.size();
                return Integer.compare(size2, size1);
            }
        });
    }



    private long price(List<Advertisement> list) {
        long res = 0;
        for (Advertisement advert : list)
            res += advert.getAmountPerOneDisplaying();
        return res;
    }

    private int duration(List<Advertisement> list) {
        int res = 0;
        for (Advertisement advert : list)
            res += advert.getDuration();
        return res;
    }
    
    //ЧУЖОЙ ХОРОШИЙ МЕТОД РЕКРУСИИ C Аrray List -Комбинаций без повторений.
    //метод, выбирающий все возможные комбинации у storage.list()
    private Set<List<Advertisement>> getSet(List<Advertisement> set, int index, ArrayList<Advertisement> subset) {
        Set<List<Advertisement>> subsets = new HashSet<>();
        if (index == set.size()) {
            subsets.add(new ArrayList<>(subset));
            return subsets;
        }
        subsets.addAll(getSet(set, index + 1, subset));
        subset.add(set.get(index));
        subsets.addAll(getSet(set, index + 1, subset));
        subset.remove(subset.size() - 1);
        return subsets;
    }

    //метод, который выбирает лучший лист
    private List<Advertisement> getList2() {
    Set<List<Advertisement>> set = getSet(storage.list(), 0, new ArrayList<Advertisement>());
    Iterator<List<Advertisement>> it = set.iterator();
       while (it.hasNext()) { //сначала отсеиваем листы, содержащие Advertisement с hits <=0, либо слишком длинные по времени
        List<Advertisement> next = it.next();
        int time = 0;
        for (Advertisement ad : next)
        {
            if(ad.getHits() <= 0) {
                it.remove();
                break;
            }
            time += ad.getDuration();
            if(time > timeSeconds)
            {
                it.remove();
                break;
            }
        }
    }
    //пункты задания 1 и 4
        return Collections.max(set, new Comparator<List<Advertisement>>()
    {
        @Override
        public int compare(List<Advertisement> o1, List<Advertisement> o2)
        {
            long price1 = price(o1), price2 = price(o2);
            if(price1 != price2) return Long.compare(price1, price2);
            int duration1 = duration(o1), duration2 = duration(o2);
            if(duration1 != duration2) return duration1 - duration2;
            return o2.size() - o1.size();
        }
    });
}

/* private int timeSeconds;

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException{

        if (storage.list().isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();

        }

        List<Advertisement> candidates = new ArrayList<>();
        //Include only compatible by duration video
        for (Advertisement ad: storage.list()) {
            if (ad.getDuration() <= timeSeconds && ad.getHits() > 0)
                candidates.add(ad);
        }

        //If no one compatible
        if (candidates.isEmpty()) {
            StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        //Select optimal video set by exhaustive search
        OptimalVideoSet optimalVideoSet = new OptimalVideoSet(candidates, timeSeconds);
        List<Advertisement> optimalVideoList = optimalVideoSet.getOptimalVideoSet();
        //Sort video playlist
        Collections.sort(optimalVideoList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int) (o1.getAmountPerOneDisplaying() == o2.getAmountPerOneDisplaying()
                        ? (o1.getAmountPerSecond() - o2.getAmountPerSecond()) * 1000000
                        : o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying());
            }
        });

        //Register event before showing videos
        StatisticManager.getInstance().register(
                new VideoSelectedEventDataRow(
                        optimalVideoList,
                        optimalVideoSet.getOptimalVideoSetAmount(),
                        optimalVideoSet.getOptimalVideoSetDuration()
                )
        );

        //Show videos & update ads' data
        for (int i = 0; i < optimalVideoList.size(); i++) {
            Advertisement showingAd = optimalVideoList.get(i);
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    showingAd.getName(),
                    showingAd.getAmountPerOneDisplaying(),
                    (int)(showingAd.getAmountPerSecond() * 1000)
            ));
            showingAd.revalidate();

        }
    }

    private class OptimalVideoSet  {
        private int toShowTimeSeconds;
        private CopyOnWriteArrayList<Advertisement> candidates_ = new CopyOnWriteArrayList<>();

        private int optimalVideoSetAmount = 0;
        private int optimalVideoSetTime = 0;
        private CopyOnWriteArrayList<Advertisement> optimalVideoSet;


        public OptimalVideoSet(List<Advertisement> candidates, int toShowTimeSeconds) {
            this.toShowTimeSeconds = toShowTimeSeconds;
            this.candidates_.addAll(candidates);
        }

        public List<Advertisement> getOptimalVideoSet() {
            Sortout(candidates_);
            List<Advertisement> result = new ArrayList<>();
            result.addAll(optimalVideoSet);
            return result;
        }

        //exhaustive search
        private void Sortout(CopyOnWriteArrayList<Advertisement> candidates) {
            CopyOnWriteArrayList<Advertisement> currentList = candidates;
            //Calculate current video set parameters
            int amountOfAd = 0;
            int sumOfAdTime = 0;
            for (Advertisement ad : currentList) {
                amountOfAd += ad.getAmountPerOneDisplaying();
                sumOfAdTime += ad.getDuration();
            }
            //If video set is too long eliminate each video
            // & invoke optimal set finding in recursion sequentially
            if (sumOfAdTime > toShowTimeSeconds)
                for (int i = currentList.size() - 1; i >= 0; i--) {
                    candidates = (CopyOnWriteArrayList<Advertisement>) currentList.clone();
                    candidates.remove(i);
                    Sortout(candidates);
                }
            else {
                //Replace optimal result if current set is better
                if (amountOfAd > optimalVideoSetAmount) {
                    optimalVideoSet = currentList;
                } else if (amountOfAd == optimalVideoSetAmount)
                    if (sumOfAdTime > optimalVideoSetTime)
                        optimalVideoSet = currentList;
                    else if (sumOfAdTime == optimalVideoSetTime)
                        if (currentList.size() < optimalVideoSet.size())
                            optimalVideoSet = currentList;
                if (optimalVideoSet == currentList) {
                    optimalVideoSetAmount = amountOfAd;
                    optimalVideoSetTime = sumOfAdTime;
                }
            }
        }

        public int getOptimalVideoSetDuration() {
            return optimalVideoSetTime;
        }

        public int getOptimalVideoSetAmount() {
            return optimalVideoSetAmount;
        }
    }*/


}
