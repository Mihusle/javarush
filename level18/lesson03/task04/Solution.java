package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(new File(reader.readLine()));
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> resList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        String minRepByte = "";
        while (in.available() > 0) {
            list.add(in.read());
        }
        int[] countArr = new int[list.size()];
        for (int i =0; i < list.size(); i++) {
            countArr[i] = 0;
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) countArr[i]++;
            }
        }
        for (int h : countArr) {
            if (h < min) min = h;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!resList.contains(list.get(i)) && countArr[i] == min) resList.add(list.get(i));
        }
        for (int i = 0; i < resList.size(); i++) {
            minRepByte += resList.get(i) + " ";
        }
        System.out.println(minRepByte);
        reader.close();
        in.close();
    }
}
