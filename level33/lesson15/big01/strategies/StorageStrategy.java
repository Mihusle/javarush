package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by Влад on 08.04.2016.
 *
 * Сокращатель ссылок будет поддерживать различные стратегии хранения данных. Общий интерфейс для всех.
 */
public interface StorageStrategy
{
    /**
     * Вернет true, если хранилище содержит ключ
     *
     * @param key
     */
    boolean containsKey(Long key);

    /**
     * true, если хранилище содержит переданное значение.
     *
     * @param value
     */
    boolean containsValue(String value);

    /**
     * Добавить в хранилище новую пару ключ-значение.
     *
     * @param key
     * @param value
     */
    void put(Long key, String value);

    /**
     * Вернуть ключ для переданного значения.
     *
     * @param value
     */
    Long getKey(String value);

    /**
     * Вернуть значение для переданного ключа.
     *
     * @param key
     */
    String getValue(Long key);
}
