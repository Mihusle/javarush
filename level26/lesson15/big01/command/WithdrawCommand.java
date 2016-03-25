package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;

/**
 * Created by Влад on 05.12.2015.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String amount = ConsoleHelper.readString();
            int expectedAmount;
            try
            {
                expectedAmount = Integer.parseInt(amount);
            }
            catch (NumberFormatException e)
            {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (expectedAmount <= 0) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                continue;
            }
            if (!manipulator.isAmountAvailable(expectedAmount))
            {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                //continue;
            }
            try
            {
                for (Map.Entry<Integer, Integer> pair : manipulator.withdrawAmount(expectedAmount).entrySet())
                {
                    ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
                }
                ConsoleHelper.writeMessage(res.getString("success.format"));
                break;
            }
            catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                continue;
            }
        }
    }
}
