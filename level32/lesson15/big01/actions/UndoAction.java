package com.javarush.test.level32.lesson15.big01.actions;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Влад on 04.04.2016.
 *
 * Класс отмены действия.
 */
public class UndoAction extends AbstractAction
{
    private View view;

    public UndoAction(View view)
    {
        this.view = view;
    }

    /*
    * Отменяет изменения при возникновении события.
    */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.undo();
    }
}
