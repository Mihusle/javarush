package com.javarush.test.level19.lesson10.home02;

import java.io.*;
import java.util.*;
/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        FileReader fileReader = new FileReader(args[0]);
        BufferedReader reader = new BufferedReader(fileReader);
        Map<String, Double> map = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] strings = line.split(" ");
            if (!map.containsKey(strings[0])) {
                map.put(strings[0], Double.parseDouble(strings[1]));
            }
            else {
                map.put(strings[0], Double.parseDouble(strings[1]) + map.get(strings[0]));
            }
        }
        reader.close();
        fileReader.close();
        Set<Double> set = new TreeSet<>();
        for (Map.Entry <String, Double> pair : map.entrySet()) {
            set.add(pair.getValue());
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue() == set.toArray()[set.toArray().length - 1]) {
                System.out.println(pair.getKey());
            }
        }
    }
}
