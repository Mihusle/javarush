package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 30.01.2016.
 */
public class ConsoleHelper
{
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Вывод в консоль
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Чтение строки с консоли
     * */
    public static String readString() throws IOException
    {
        return reader.readLine();

    }

    /**
     * Просит выбрать блюдо и добавляет его в список заказов
     * @return список блюд - заказ
     * */
    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> dishes = new ArrayList<>();
        writeMessage("Choose dish : " + Dish.allDishesToString());
        while (true)
        {
            String dish = readString();
            if (!dish.equalsIgnoreCase("exit")) {
                try
                {
                    dishes.add(Dish.valueOf(dish));
                }
                catch (IllegalArgumentException e) {
                    writeMessage(dish + " is not detected");
                }
            }
            else break;
        }
        return dishes;
    }
}
