package com.javarush.test.level18.lesson10.home02;
import java.io.*;
/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран частоту встречания пробела. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int countAll = 0, countSpace = 0;
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        while (fileInputStream.available() > 0) {
            int data = fileInputStream.read();
            countAll++;
            if (data == Integer.valueOf(' ')) {
                countSpace++;
            }
        }
        fileInputStream.close();
        if (countSpace == 0) {

        }
        else
        {
            double frequency = (double) countSpace / countAll * 100;
            System.out.printf("%.2f", frequency);
        }
    }
}
