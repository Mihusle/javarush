package com.javarush.test.level18.lesson10.home03;
import java.io.*;
/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
Темповые файлы создавать нельзя, т.к. на сервере заблокирована возможность создания каких любо файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream fileOutputStream1 = new FileOutputStream(reader.readLine());
        FileInputStream fileInputStream2 = new FileInputStream(reader.readLine());
        FileInputStream fileInputStream3 = new FileInputStream(reader.readLine());
        while (fileInputStream2.available() > 0) {
            int data = fileInputStream2.read();
            fileOutputStream1.write(data);
        }
        while (fileInputStream3.available() > 0) {
            int data = fileInputStream3.read();
            fileOutputStream1.write(data);
        }
        fileInputStream2.close();
        fileInputStream3.close();
        fileOutputStream1.close();
        reader.close();
    }
}
