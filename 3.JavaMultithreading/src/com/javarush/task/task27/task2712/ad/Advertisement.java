package com.javarush.task.task27.task2712.ad;

/**
 * Created by Sfill on 01.08.2017.
 */
public class Advertisement {
   private Object content;          // — видео
    private String name;            // — имя/название
    private long initialAmount;     // — начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private int hits;               //  — количество оплаченных показов
    private int duration;           // — продолжительность в секундах

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying=initialAmount/hits; // Стоимость одного показа ролика / Количество в одном Отображение
    }

    public double getAmountPerSecond() {
        return (double)amountPerOneDisplaying / duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void setAmountPerOneDisplaying(long amountPerOneDisplaying) {
        this.amountPerOneDisplaying = amountPerOneDisplaying;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getHits() {
        return hits;
    }

    public void revalidate(){
        /*if(hits>0){
            hits--;
        }else throw new UnsupportedOperationException();*/
        if(hits<=0){
            throw new NoVideoAvailableException();}
        else
        { hits--;}
    }
}
