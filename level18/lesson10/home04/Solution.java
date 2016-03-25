package com.javarush.test.level18.lesson10.home04;
import java.io.*;
/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        FileInputStream fileInputStream1 = new FileInputStream(fileName1);
        FileInputStream fileInputStream2 = new FileInputStream(reader.readLine());
        byte[] data1 = new byte[0];
        byte[] data2 = new byte[0];
        while (fileInputStream1.available() > 0) {
            data1 = new byte[fileInputStream1.available()];
            int count1 = fileInputStream1.read(data1);
        }
        while (fileInputStream2.available() > 0) {
            data2 = new byte[fileInputStream2.available()];
            int count2 = fileInputStream2.read(data2);
        }
        fileInputStream1.close();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
        byte[] result = new byte[data1.length + data2.length];
        System.arraycopy(data2, 0, result, 0, data2.length);
        System.arraycopy(data1, 0, result, data2.length, data1.length);
        fileOutputStream.write(result);
        fileInputStream2.close();
        fileOutputStream.close();
        reader.close();
    }
}
