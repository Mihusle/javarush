package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

/**
 * Created by Влад on 08.04.2016.
 *
 * Класс для любой строки может вернуть уникальный идентификатор, а для идентификатора вернуть строку.
 * - для двух одинаковых строк возвращается один и тот же идентификатор.
 * - поддерживает столько строк, сколько значений может принимать Long.
 */
public class Shortener
{
    private Long lastId = 0l; //Последнее значение идентификатора, которое было использовано при добавлении строки в хранилище.
    private StorageStrategy storageStrategy; //Стратегия хранения данных.

    public Shortener(StorageStrategy storageStrategy)
    {
        this.storageStrategy = storageStrategy;
    }

    /*
     * Будет возвращать идентификатор id для заданной строки.
     */
    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string)) {
            return storageStrategy.getKey(string);
        }
        else {
            storageStrategy.put(++lastId, string);
            return lastId;
        }
    }

    /*
    * Будет возвращать строку для заданного идентификатора или null, если передан неверный идентификатор.
    */
    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
