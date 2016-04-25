package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Влад on 08.04.2016.
 */
public class Solution
{
    public static void main(String[] args)
    {
        StorageStrategy storageStrategy = new HashMapStorageStrategy();
        testStrategy(storageStrategy, 10000l);
        StorageStrategy storageStrategy1 = new OurHashMapStorageStrategy();
        testStrategy(storageStrategy1, 10000l);
        StorageStrategy storageStrategy3 = new OurHashBiMapStorageStrategy();
        testStrategy(storageStrategy3, 10000l);
        StorageStrategy storageStrategy4 = new HashBiMapStorageStrategy();
        testStrategy(storageStrategy4, 10000l);
        StorageStrategy storageStrategy5 = new DualHashBidiMapStorageStrategy();
        testStrategy(storageStrategy5, 10000l);
        StorageStrategy storageStrategy2 = new FileStorageStrategy();
        testStrategy(storageStrategy2, 10000l);

    }

    /**
     * Для переданного множества строк возвращает множество идентификаторов.
     *
     * @param shortener используется для получения идентификатора каждой отдельной строки.
     * @param strings множество строк
     *
     * @return множество идентификаторов.
     */
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> result = new HashSet<>();
        for (String s : strings) {
            result.add(shortener.getId(s));
        }
        return result;
    }

    /**
     * Для переданного множество идентификаторов возвращает множество строк.
     *
     * @param shortener используется для получения строки каждого идентификатора.
     * @param keys множество идентификаторов.
     *
     * @return нмножество строк.
     */
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> result = new HashSet<>();
        for (Long l : keys) {
            result.add(shortener.getString(l));
        }
        return result;
    }

    /**
     * Тестирует работу стратегии на определенном количестве элементов.
     *
     * @param strategy стратегия для тестирования.
     * @param elementsNumber количество элементов.
     */
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSetOfStrings1 = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testSetOfStrings1.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Set<Long> testSetOfLong;

        Date start1 = new Date();
        testSetOfLong = getIds(shortener, testSetOfStrings1);
        Date finish1 = new Date();
        long difference1 = finish1.getTime() - start1.getTime();
        Helper.printMessage(Long.toString(difference1)); //Время работы getIds()

        Set<String> testSetOfStrings2;

        Date start2 = new Date();
        testSetOfStrings2 = getStrings(shortener, testSetOfLong);
        Date finish2 = new Date();
        long difference2 = finish2.getTime() - start2.getTime();
        Helper.printMessage(Long.toString(difference2)); //Время работы getStrings()

        if (testSetOfStrings1.equals(testSetOfStrings2)) {
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
