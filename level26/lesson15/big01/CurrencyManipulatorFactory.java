package com.javarush.test.level26.lesson15.big01;

import java.util.*;

/**
 * Created by Влад on 03.12.2015.
 */
public class CurrencyManipulatorFactory
{
    static HashMap<String, CurrencyManipulator> manipulators = new HashMap<>();
    private CurrencyManipulatorFactory()
    {
    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (manipulators.containsKey(currencyCode)) {
            return manipulators.get(currencyCode);
        } else {
            CurrencyManipulator cur = new CurrencyManipulator(currencyCode);
            manipulators.put(currencyCode, cur);
            return cur;
        }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return manipulators.values();
    }
}
