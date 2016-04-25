package com.javarush.test.level32.lesson15.big01.listeners;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by Влад on 04.04.2016.
 *
 * Этот слушатель будет следить за меню, а если конкретнее,
 то за моментом, когда меню редактирования будет выбрано пользователем. В этот момент он
 будет запрашивать у представления можем ли мы сейчас отменить или вернуть какое-то
 действие, и в зависимости от этого делать доступными или не доступными пункты меню
 "Отменить" и "Вернуть".
 */
public class UndoMenuListener implements MenuListener
{
    private View view;
    private JMenuItem undoMenuItem; //Пункт меню "Отменить".
    private JMenuItem redoMenuItem; //Пункт меню "Вернуть".

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem)
    {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }


    /**
     * Вызывается перед показом меню.
     * Проверяет доступны ли отмена или возврат действия.
     * В зависимости от результата делает доступными или недоступными соответствующие пункты меню.
     */
    @Override
    public void menuSelected(MenuEvent e)
    {
        if (view.canUndo()) {
            //Если отмена доступна,
            undoMenuItem.setEnabled(true); //Сделать пункт меню для отмены доступным.
        }
        else {
            undoMenuItem.setEnabled(false); //Иначе сделать недоступным.
        }
        //То же самое для возврата.
        if (view.canRedo()) {
            redoMenuItem.setEnabled(true);
        }
        else {
            redoMenuItem.setEnabled(false);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e)
    {

    }

    @Override
    public void menuCanceled(MenuEvent e)
    {

    }
}
