package com.javarush.test.level18.lesson10.home08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/
public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> names = new ArrayList<>();
        String fileName;
        while (!(fileName = reader.readLine()).equals("exit")) {
            names.add(fileName);
        }
        for (String s : names) {
            new ReadThread(s).start();
        }
        reader.close();
    }

    public static class ReadThread extends Thread {
        private String fileName;
        public ReadThread(String fileName)
        {
            this.fileName = fileName;
        }
        public void run() {
            ArrayList<Integer> list = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            try
            {
                FileInputStream in = new FileInputStream(fileName);
                while (in.available() > 0) {
                    list.add(in.read());
                }
                int[] countArr = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    countArr[i] = 0;
                    for (int j = i+1; j < list.size(); j++) {
                        if (list.get(i).equals(list.get(j))) countArr[i]++;
                    }
                }
                for (int h : countArr) {
                    if (h > max) max = h;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (countArr[i] == max) {
                        resultMap.put(fileName, list.get(i));
                    }
                }
                in.close();
            }
            catch (IOException e) {}
        }
    }
}
