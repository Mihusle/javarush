package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 13.02.2016.
 * Provider - то же, что и Context. Осуществляет взаимодействие с конкретными алгоритмами.
 * Посылает запросы Strategy от класса-клиента. Поэтому содержит ссылку на Strategy.
 */
public class Provider
{
    private Strategy strategy;

    public Provider(Strategy strategy)
    {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }

    /**
     * Берем вакансии с конкретного сайта.
     * Это достигается использованием конкретной реализации Strategy.
     * То есть для каждого сайта вызовется нужный метод, и для каждого сайта мы получим свой список вакансий.
     *
     * @param searchString - поисковый запрос.
     * @return List<Vacancy> list - список вакансий с конкретного сайта.*/
    public List<Vacancy> getJavaVacancies(String searchString) throws IOException
    {
        return strategy.getVacancies(searchString);
    }
}
