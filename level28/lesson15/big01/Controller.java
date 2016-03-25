package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Влад on 13.02.2016.
 * Controller решает, какие данные нужны, и обращается к нужной модели.
 * Т.е. содержит ссылку на модель, чтобы выбрать нужную бизнес-логику.
 */
public class Controller
{
    private Model model;

    public Controller(Model model)
    {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    /**
     * Выбираем город, в нужной модели работаем с этим городом.
     *
     * @param city - имя города.
     */
    public void onCitySelect(String city) {
        model.selectCity(city);
    }
}
