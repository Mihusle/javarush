package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;

/**
 * Created by Влад on 10.04.2016.
 *
 * Получение идентификатора для строки требует много времени. Данная стратегия будет лишена этого недостатка.
 * Это достигается путем хранения ключей и значений в двух соответствиях. В итоге поиск будет всегда по ключам, а не по значениям.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy
{
    /**
     * Хранит соостветствие ключа и значения.
     */
    private HashMap<Long, String> k2v = new HashMap<>();

    /**
     * Хранит соотвтетствие значения и ключа.
     */
    private HashMap<String, Long> v2k = new HashMap<>();

    /**
     * Проверяет, есть ли такой ключ.
     * Проверка осуществляется в первой HashMap, т.к. там Long ключи.
     */
    @Override
    public boolean containsKey(Long key)
    {
        return k2v.containsKey(key);
    }

    /**
     * Проверяет, есть ли такое значение.
     * Проверка осуществаляется во второй HashMap, т.к. там String ключи.
     */
    @Override
    public boolean containsValue(String value)
    {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value)
    {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    /**
     * Возвращает ключ.
     * Использует значение ключа во второй HashMap.
     */
    @Override
    public Long getKey(String value)
    {
        return v2k.get(value);
    }

    /**
     * Возвращает значение.
     * Использует значение ключа в первой HashMap.
     */
    @Override
    public String getValue(Long key)
    {
        return k2v.get(key);
    }
}
