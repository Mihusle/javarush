package com.javarush.test.level31.lesson06.bonus01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws IOException
    {
        File resultFile = new File(args[0]);
        ArrayList<File> files = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            files.add(new File(args[i]));
        }
        Collections.sort(files); //Имена могут приходить в случайном порядке.
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (File f : files) {
            FileInputStream fis = new FileInputStream(f);
            while (fis.read(buffer) > -1) {
                baos.write(buffer); //Пишем содержимое всех файлов в baos.
            }
            fis.close();
        }
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(baos.toByteArray())); //На основе содержимого baos создаем поток для чтения из архива.
        FileOutputStream fos = new FileOutputStream(resultFile); //Поток для записи результата.
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            int count;
            while ((count = zis.read(buffer)) > -1) {
                fos.write(buffer, 0, count); //В случае записи из архива используется именно такая запись.
            }
            zipEntry = zis.getNextEntry();
        }
        fos.close();
        zis.close();
    }
}
