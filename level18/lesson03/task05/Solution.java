package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(new File(reader.readLine()));
        TreeSet<Integer> treeSet = new TreeSet<>();
        while (in.available() > 0) {
            treeSet.add(in.read());
        }
        for (int element : treeSet) {
            System.out.print(element + " ");
        }
        in.close();
        reader.close();
    }
}
