package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.*;
import java.util.ResourceBundle;
import java.util.regex.*;

/**
 * Created by Влад on 03.12.2015.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String tt = "";
        try
        {
            tt = reader.readLine();
            if(tt.equalsIgnoreCase("EXIT")){
                throw new InterruptOperationException();
            }
        }
        catch (IOException e)
        {
        }
        return tt;
    }

    public static String askCurrencyCode() throws InterruptOperationException
    {
        boolean isOk = false;
        String valuta = null;
        while(!isOk)
        {
            System.out.println(res.getString("choose.currency.code"));
            valuta = readString();
            Pattern p = Pattern.compile("^.*\\d+.*$");
            Matcher m = p.matcher(valuta);
            if(valuta.length() == 3 && !m.matches()) isOk = true;
            else {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
        return valuta.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
    {
        boolean isOk = false;
        String two_digits;
        String[] digits = new String[0];
        while(!isOk)
        {
            ConsoleHelper.writeMessage(res.getString("choose.denomination.and.count.format"));
            two_digits = readString();
            try {
                digits = two_digits.split(" ");
                if(digits.length == 2 && Integer.parseInt(digits[0]) > 0 && Integer.parseInt(digits[1]) > 0)
                    isOk = true;
                else {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                }
            }
            catch (Exception e){
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
        return digits;
    }

    public static Operation askOperation() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        while(true)
        {
            try
            {
                int oper = Integer.parseInt(readString());
                Operation op = Operation.getAllowableOperationByOrdinal(oper);
                return op;
            }
            catch (Exception e)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
