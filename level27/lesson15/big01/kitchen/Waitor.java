package com.javarush.test.level27.lesson15.big01.kitchen;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Влад on 31.01.2016.
 */
public class Waitor implements Observer
{
    @Override
    public void update(Observable o, Object arg)
    {
        Order order = (Order) arg;
        Cook cook = (Cook) o;
        System.out.println(order + " was cooked by " + cook);
    }
}
