package com.javarush.test.level19.lesson05.task04;

import java.io.*;
/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
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
            String s = fileReader.readLine();
            s = s.replaceAll("\\.", "!");
            fileWriter.write(s);
            fileWriter.newLine();
        }
        fileReader.close();
        fileWriter.close();
    }
}
