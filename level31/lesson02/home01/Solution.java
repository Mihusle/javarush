package com.javarush.test.level31.lesson02.home01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* Проход по дереву файлов
1. На вход метода main подаются два параметра.
Первый - path - путь к директории, второй - resultFileAbsolutePath - имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его.
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке
2.2.2. переименовать resultFileAbsolutePath в 'allFilesContent.txt'
2.2.3. в allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять "\n"
2.3. Удалить директории без файлов (пустые).
Все файлы имеют расширение txt.
*/

public class Solution {

    public static void main(String[] args) throws IOException
    {
        String filePath = args[0];
        String resultFileAbsolutePath = args[1];
        /*String filePath = "D:/JavaTest/";
        String resultFileAbsolutePath = "D:/testFile.txt";
        File resultFile = new File(resultFileAbsolutePath);*/
        File resultFile = new File(resultFileAbsolutePath);
        recFileSearch(filePath);
        files.remove(new File(resultFileAbsolutePath));
        resultFile.renameTo(new File(resultFile.getParent() + "/" + "allFilesContent.txt"));
        Collections.sort(files, new Comparator<File>()
        {
            @Override
            public int compare(File o1, File o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFile));
        for (File file : files) {
            //System.out.println(file.getName());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.newLine();
            }
            bufferedReader.close();
        }
        bufferedWriter.close();
    }

    private static ArrayList<File> files = new ArrayList<>();

    private static void recFileSearch(String filePath) {
        File directory = new File(filePath);
        if (!directory.isDirectory()) {
            return;
        }
        else if (directory.listFiles().length == 0) {
            directory.delete();
        }
        else {
            for (File file : directory.listFiles()) {
                if (file.isDirectory()) {
                    recFileSearch(file.getAbsolutePath());
                }
                else if (file.length() > 50) {
                    file.delete();
                }
                else {
                    files.add(file);
                }
            }
        }
    }
}
