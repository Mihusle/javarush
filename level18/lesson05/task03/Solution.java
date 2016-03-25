package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(new File(reader.readLine()));
        FileOutputStream out1 = new FileOutputStream(new File(reader.readLine()));
        FileOutputStream out2 = new FileOutputStream(new File(reader.readLine()));
        while (in.available() > 0) {
            if (in.available() % 2 == 0) {
                byte[] arr1 = new byte[in.available()/2];
                byte[] arr2 = new byte[in.available()/2];
                int count1 = in.read(arr1);
                int count2 = in.read(arr2);
                out1.write(arr1, 0, count1);
                out2.write(arr2, 0, count2);
            }
            else {
                byte[] arr1 = new byte[in.available()/2 + 1];
                byte[] arr2 = new byte[in.available()/2];
                int count1 = in.read(arr1);
                int count2 = in.read(arr2);
                out1.write(arr1, 0, count1);
                out2.write(arr2, 0, count2);
            }
        }
        reader.close();
        in.close();
        out1.close();
        out2.close();
    }
}
