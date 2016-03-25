package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 29.01.2016.
 */
public class Order
{
    private List<Dish> dishes;
    private Tablet tablet;

    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString()
    {
        return this.isEmpty() ? "" : "Your order: " + dishes.toString() + " of " + tablet.toString();

    }

    /**
     * @return Общее время приготовления заказа
     */
    public int getTotalCookingTime() {
        int total = 0;
        for (Dish d : dishes) {
            total += d.getDuration();
        }
        return total;
    }

    /**
     * Проверяет пуст ли заказ
     */
    public boolean isEmpty() {
        return dishes.isEmpty();
    }
}
