package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 21.02.2016.
 * Model получает данные и передает их View для отображения. Потому содержит ссылку на View.
 */
public class Model
{
    private View view; //Сюда передаются данные для отображения.
    private Provider[] providers; //Список всех провайдеров для работы сразу с несколькими сайтами.

    public Model(View view, Provider[] providers)
    {
        if (view == null || providers == null || providers.length == 0) throw new IllegalArgumentException();
        this.view = view;
        this.providers = providers;
    }

    /**
     * Получение данных с сайтов.
     *
     * @param city - название города.
     */
    public void selectCity(String city) {
        List<Vacancy> listOfVacancy = new ArrayList<>(); //Список полученных вакансий.
        for (Provider p : providers) {
            try
            {
                listOfVacancy.addAll(p.getJavaVacancies(city)); //Заполняем список, работая со всеми провайдерами
            }
            catch (IOException e)
            {

            }
        }
        view.update(listOfVacancy); //Передаем список вакансий для представления пользователю.
    }
}
