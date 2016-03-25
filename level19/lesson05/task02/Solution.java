package com.javarush.test.level19.lesson05.task02;

import java.io.*;
/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader fileReader = new FileReader(reader.readLine());
        reader.close();
        int count = 0;
        String s = null;
        while (fileReader.ready()) {
            s += (char)fileReader.read();
        }
        s = s.replaceAll("\\p{P}", " ").toLowerCase();
        s = s.replaceAll("\\s", " ");
        String[] strings = s.split(" ");
        for (String x : strings) {
            if (x.equals("world")) {
                count++;
            }
        }
        System.out.print(count);
        fileReader.close();
    }
}
