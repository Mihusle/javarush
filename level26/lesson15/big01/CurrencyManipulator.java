package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.*;

/**
 * Created by Влад on 03.12.2015.
 */
public class CurrencyManipulator
{
    private String currencyCode;

    private Map<Integer,Integer> denominations = new HashMap<>();

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count)
    {
        if (denominations.containsKey(denomination))
        {
            denominations.put(denomination, denominations.get(denomination) + count);
        }
        else
        {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount(){
        int sum = 0;
        for(Map.Entry<Integer,Integer> pair : denominations.entrySet()){
            sum += (pair.getKey() * pair.getValue());
        }
        return sum;
    }

    public boolean hasMoney(){
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount)
    {
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
    {
        Map<Integer, Integer> denom = new TreeMap<>(Collections.reverseOrder());
        denom.putAll(denominations);
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Integer, Integer> pair : denom.entrySet())
        {
            int value = expectedAmount / pair.getKey();
            if (value == 0) continue;
            result.put(pair.getKey(), value);
            expectedAmount = expectedAmount % pair.getKey();
            if (expectedAmount == 0) break;
        }
        if (expectedAmount != 0) {
            throw new NotEnoughMoneyException();
        }
        for (Map.Entry<Integer, Integer> pairRes : result.entrySet()) {
            for (Map.Entry<Integer, Integer> pairDen : denom.entrySet()) {
                if (pairRes.getKey() == pairDen.getKey()) {
                    if (pairRes.getValue() == pairDen.getValue()) {
                        denominations.remove(pairDen.getKey());
                    }
                    else {
                        denominations.put(pairDen.getKey(), pairDen.getValue() - pairRes.getValue());
                    }
                }
            }
        }
        return result;
    }
}
