package com.javarush.test.level19.lesson08.task04;

import java.io.*;
/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleStream = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(out);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(consoleStream);
        String res = out.toString();
        String[] strings = res.split(" ");
        if (strings[1].equals("+")) {
            int first = Integer.parseInt(strings[0]);
            int second = Integer.parseInt(strings[2]);
            res = strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + String.valueOf(first + second);
        }
        else if (strings[1].equals("*")) {
            int first = Integer.parseInt(strings[0]);
            int second = Integer.parseInt(strings[2]);
            res = strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + String.valueOf(first * second);
        }
        else if (strings[1].equals("-")) {
            int first = Integer.parseInt(strings[0]);
            int second = Integer.parseInt(strings[2]);
            res = strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " " + String.valueOf(first - second);
        }
        System.out.println(res);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

