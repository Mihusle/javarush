package com.javarush.test.level18.lesson10.home06;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
/* Встречаемость символов
Программа запускается с одним параметром - именем файла, который содержит английский текст.
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете). Пример: ','=44, 's'=115, 't'=116
Вывести на консоль отсортированный результат:
[символ1]  частота1
[символ2]  частота2
Закрыть потоки. Не использовать try-with-resources

Пример вывода:
, 19
- 7
f 361
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        ArrayList<Character> list = new ArrayList<>();
        Map<Character, Integer> resList = new TreeMap<>();
        FileInputStream in = new FileInputStream(args[0]);
        while (in.available() > 0) {
            list.add((char)in.read());
        }
        int[] countArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            countArr[i] = 0;
            for (int j = 0; j < list.size(); j++)
            {
                if (list.get(i).equals(list.get(j)))
                {
                    countArr[i]++;
                }
            }
            if (!resList.containsKey(list.get(i))) {
                resList.put(list.get(i), countArr[i]);
            }
        }
        for (Entry<Character, Integer> entry : resList.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        in.close();
    }
}
