package com.javarush.test.level32.lesson15.big01.actions;

import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Влад on 04.04.2016.
 *
 * Класс возврата действия.
 */
public class RedoAction extends AbstractAction
{
    private View view;

    public RedoAction(View view)
    {
        this.view = view;
    }

    /**
     * Возврат изменений при определенном событии.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        view.redo();
    }
}
