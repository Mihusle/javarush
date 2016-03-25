package com.javarush.test.level19.lesson05.task01;
import java.io.*;
import java.util.ArrayList;
/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        FileReader reader = new FileReader(bufReader.readLine());
        FileWriter writer = new FileWriter(bufReader.readLine());
        bufReader.close();
        ArrayList<Integer> list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.read());
        }
        reader.close();
        for (int i = 1; i < list.size(); i += 2) {
            writer.write(list.get(i));
        }
        writer.close();
    }
}
