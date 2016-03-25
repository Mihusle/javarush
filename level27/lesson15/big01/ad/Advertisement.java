package com.javarush.test.level27.lesson15.big01.ad;

/**
 * Created by Влад on 31.01.2016.
 * Рекламное объявление
 */
public class Advertisement
{
    private Object content;
    private String name;
    private long initialAmount; //Начальная сумма, стоимость рекламы в копейках
    private int hits; //Число оплаченных показов
    private int duration; //Продолжительность в секундах
    private long amountPerOneDisplaying; //Стоимость одного рекламного объявления в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration)
    {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    public long getAmountPerOneDisplaying()
    {
        return amountPerOneDisplaying;
    }

    public int getHits()
    {
        return hits;
    }

    public void revalidate() {
        if (hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }
}
