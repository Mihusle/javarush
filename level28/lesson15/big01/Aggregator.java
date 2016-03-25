package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.HHStrategy;
import com.javarush.test.level28.lesson15.big01.model.Model;
import com.javarush.test.level28.lesson15.big01.model.MoikrugStrategy;
import com.javarush.test.level28.lesson15.big01.model.Provider;
import com.javarush.test.level28.lesson15.big01.view.HtmlView;

import java.io.IOException;

/**
 * Created by Влад on 13.02.2016.
 * Сам агрегатор.
 */
public class Aggregator
{
    public static void main(String[] args) throws IOException
    {
        Provider[] providers = {
                                    new Provider(new HHStrategy()),
                                    new Provider(new MoikrugStrategy())
                                }; //Массив провайдеров. В данном случае содержит лишь объект для работы с сайтом hh.ru.
        HtmlView view = new HtmlView(); //Объект для представления данных пользователю.
        Model model = new Model(view, providers); //Модель будет работать со всеми провайдерами и передавать полученные данные для представления.
        Controller controller = new Controller(model); //Контроллер обрашается к модели.
        view.setController(controller); //Устанавливаем, куда передаем данные о событиях (конкретно здесь - поисковый запрос.).
        view.userCitySelectEmulationMethod(); //Делаем поисковый запрос.
    }
}
