package com.javarush.test.level18.lesson10.home01;
import java.io.*;
/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        int count = 0;
        FileInputStream inputStream = new FileInputStream(args[0]);
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            if (data >= 65 && data <= 90 || data >= 97 && data <= 122) {
                count++;
            }
        }
        System.out.println(count);
        inputStream.close();
    }
}
