package com.javarush.test.level31.lesson02.home02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.PriorityQueue;
import java.util.Queue;

/* Находим все файлы
Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
Используйте очередь, рекурсию не используйте.
Верните список всех путей к найденным файлам, путь к директориям возвращать не надо.
Путь должен быть абсолютный.
*/
public class Solution {

    public static List<String> getFileTree(String root) throws IOException {
        List<String> result = new ArrayList<>();
        File fileRoot = new File(root);
        Queue<File> queue = new PriorityQueue<>();
        for (File file : fileRoot.listFiles()) {
            if (file.isDirectory()) {
                queue.offer(file);
            }
            else {
                result.add(file.getAbsolutePath());
            }
        }
        while (!queue.isEmpty()) {
            File file = queue.poll();
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    queue.offer(f);
                }
                else {
                    result.add(f.getAbsolutePath());
                }
            }
        }
        return result;
    }
}
