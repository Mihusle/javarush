package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Component;

/**
 * Created by Влад on 04.04.2016.
 */
public class TextEditMenuListener implements MenuListener
{
    private View view;

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent)
    {
        JMenu jMenu = (JMenu) menuEvent.getSource(); //Получение объекта, над которым было совершено действие.
        Component[] components = jMenu.getMenuComponents(); //Список компонентов меню.
        for (Component component : components) {
            component.setEnabled(view.isHtmlTabSelected()); //Каждый компонент становится видимым, если выбрана вкладка html-редактирования.
        }
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent)
    {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent)
    {

    }
}
