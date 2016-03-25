package com.javarush.test.level30.lesson15.big01;

import java.io.*;
/**
 * Created by Влад on 07.03.2016.
 *
 * Класс-помощник для работы с консолью.
 */
public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Вывод сообщения на экран.
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Чтение сообщения с консоли.
     */
    public static String readString() {
        String string;
        while (true)
        {
            try
            {
                string = reader.readLine();
                break;
            }
            catch (IOException e)
            {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return string;
    }

    /**
     * Чтение чисел с консоли.
     */
    public static int readInt() {
        int result;
        while (true) {
            try
            {
                result = Integer.parseInt(readString());
                break;
            }
            catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
        return result;
    }
}
