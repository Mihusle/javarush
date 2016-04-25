package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Влад on 03.04.2016.
 *
 * Слушатель событий.
 */
public class FrameListener extends WindowAdapter
{
    private View view;

    public FrameListener(View view)
    {
        this.view = view;
    }

    /*
    * Закрытие окна - завершение работы приложения.
    */
    @Override
    public void windowClosing(WindowEvent e)
    {
        view.exit();
    }
}
