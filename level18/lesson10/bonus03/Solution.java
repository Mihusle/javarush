package com.javarush.test.level18.lesson10.bonus03;

import java.io.*;
import java.util.ArrayList;
/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String id, line;
        ArrayList<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        if (args[0].equals("-u")) {
            for (int i = 0; i < list.size(); i++) {
                id = list.get(i).substring(0, 8).trim();
                if (id.equals(args[1])) {
                    while (args[1].length() < 8) {
                        args[1] += " ";
                    }
                    while (args[2].length() < 30) {
                        args[2] += " ";
                    }
                    while (args[3].length() < 8) {
                        args[3] += " ";
                    }
                    while (args[4].length() < 4) {
                        args[4] += " ";
                    }
                    String newString = args[1] + args[2] + args[3] + args[4];
                    list.remove(i);
                    list.add(i, newString);
                }
            }
            for (String s : list) {
                bw.write(s);
            }
        }
        else {
            for (String s : list) {
                id = s.substring(0, 8).trim();
                if (id.equals(args[1])) list.remove(s);
            }
            for (String s : list) {
                bw.write(s);
            }
        }
        bw.close();
    }
}
