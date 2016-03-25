package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by Влад on 05.12.2015.
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String curCode = ConsoleHelper.askCurrencyCode();
        String[] str = ConsoleHelper.getValidTwoDigits(curCode);
        CurrencyManipulator currentManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(curCode);
        try {
            int digit1 = Integer.parseInt(str[0]);
            int digit2 = Integer.parseInt(str[1]);

            currentManipulator.addAmount(digit1, digit2);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), digit1, digit2));
        }
        catch (NumberFormatException e) {
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }
}
