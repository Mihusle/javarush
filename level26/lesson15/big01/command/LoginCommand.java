package com.javarush.test.level26.lesson15.big01.command;

import java.util.ResourceBundle;
import java.util.regex.*;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by Влад on 18.12.2015.
 */
public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle("com.javarush.test.level26.lesson15.big01.resources.verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            String number = ConsoleHelper.readString();
            Pattern p1 = Pattern.compile("^\\d{12}$");
            Matcher m1 = p1.matcher(number);
            String pin = ConsoleHelper.readString();
            Pattern p2 = Pattern.compile("^\\d{4}$");
            Matcher m2 = p2.matcher(pin);
            if (m1.matches() && m2.matches())
            {
                if (validCreditCards.containsKey(number) && validCreditCards.getString(number).equals(pin)) {
                    ConsoleHelper.writeMessage(String.format(number, res.getString("success.format")));
                    break;
                }
                else {
                    ConsoleHelper.writeMessage(String.format(number, res.getString("not.verified.format")));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                    continue;
                }
            }
            else {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                continue;
            }
        }
    }
}
