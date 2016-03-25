package com.javarush.test.level19.lesson10.home08;

import java.io.*;
/* Перевертыши
1 Считать с консоли имя файла.
2 Для каждой строки в файле:
2.1 переставить все символы в обратном порядке
2.2 вывести на экран
3 Закрыть потоки. Не использовать try-with-resources

Пример тела входного файла:
я - программист.
Амиго

Пример результата:
.тсиммаргорп - я
огимА
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        while (in.ready()) {
            StringBuilder stringBuilder = new StringBuilder(in.readLine());
            stringBuilder.reverse();
            System.out.println(stringBuilder.toString());
        }
        in.close();
    }
}
