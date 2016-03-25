package com.javarush.test.level18.lesson10.home05;
import java.io.*;
/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
        while (fileInputStream.available() > 0) {
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            fileOutputStream.write(getInput(data));
        }
        reader.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static byte[] getInput(byte[] data) {
        StringBuilder builder = new StringBuilder();
        String[] arrOfStrings = new String(data).split(" ");
        byte[] res;
        long[] temp = new long[arrOfStrings.length];
        for (int i = 0; i < arrOfStrings.length; i++) {
            temp[i] = Math.round(Double.valueOf(arrOfStrings[i]));
        }
        for (long i : temp) {
            builder.append(i);
            builder.append(" ");
        }
        res = builder.toString().getBytes();
        return res;
    }
}
