package com.javarush.test.level19.lesson05.task05;

import java.io.*;
import java.util.regex.Pattern;
/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();
        while (fileReader.ready()) {
            char c = (char) fileReader.read();
            if (!Pattern.matches("\\p{P}", String.valueOf(c))) {
                fileWriter.write(c);
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
