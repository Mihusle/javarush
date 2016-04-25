package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.Serializable;

/**
 * Created by Влад on 08.04.2016.
 *
 * В реализации HashMap вложенный класс Entry предоставляет механизм хранения ключей и значений.
 *
 * В этой реализации Entry не будет вложенным и будет поддерживать лишь Serializable.
 */
public class Entry implements Serializable
{
    Long key; //Ключ - идентификатор типа Long в нашем случае.
    String value; //Значения - строка.
    Entry next; //Entry должен предоставлять доступ к следующему за ним объекту, поэтому содержит ссылку на него.
    int hash; //Хеш-значение. Используется для определения позиции в массиве, куда будет помещен Entry (Все экземпляры Entry хранятся в массиве.).

    public Entry(int hash, Long key, String value, Entry next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public String getValue()
    {
        return value;
    }

    public Long getKey()
    {
        return key;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "Entry{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", next=" + next +
                ", hash=" + hash +
                '}';
    }
}
