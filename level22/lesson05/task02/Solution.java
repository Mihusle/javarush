package com.javarush.test.level22.lesson05.task02;

/* Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException
    {
        if (string == null) {
            throw new TooShortStringException();
        }
        if (string.equals("")) {
            throw new TooShortStringException();
        }
        int first = string.indexOf('\t');
        if (first == -1) {
            throw new TooShortStringException();
        }
        int second = string.indexOf('\t', first + 1);
        if (second == -1) {
            throw new TooShortStringException();
        }
        return string.substring(first + 1, second);
    }

    public static class TooShortStringException extends Exception {
    }
}
