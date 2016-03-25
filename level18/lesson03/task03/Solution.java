package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(new File(reader.readLine()));
        ArrayList <Long> list = new ArrayList<>();
        long countRep = 0, count = 0, maxRepByte = 0;

        while (in.available() > 0) {
            list.add((long) in.read());
        }
        for (long firstByte : list) {
            for (long secondByte : list) {
                if (firstByte == secondByte) countRep++;
            }
            if (countRep > count) {
                count = countRep;
                maxRepByte = firstByte;
            }
            countRep = 0;
        }
        System.out.println(maxRepByte);
        reader.close();
        in.close();
    }
}
