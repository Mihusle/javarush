package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 13.02.2016.
 * Интерфейс, определяющий, как будут использоваться различные алгоритмы.
 * Стратегии - алгоритмы работы с конкретным сайтом. Т.к. сайты разные, то и алгоритмы различны, и для каждого нужно создать свой класс.
 */
public interface Strategy
{
    List<Vacancy> getVacancies(String searchString) throws IOException;
}
