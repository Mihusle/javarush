package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 08.04.2016.
 *
 * Стратегия хранилища с помощью HashMap.
 */
public class HashMapStorageStrategy implements StorageStrategy
{
    private HashMap<Long, String> data = new HashMap<>();
    
    @Override
    public boolean containsKey(Long key)
    {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value)
    {
        Long key = null;
        for (Map.Entry<Long, String> pair : data.entrySet()) {
            if (pair.getValue().equals(value)) {
                key = pair.getKey();
                break;
            }
        }
        return key;
    }

    @Override
    public String getValue(Long key)
    {
        return data.get(key);
    }
}
