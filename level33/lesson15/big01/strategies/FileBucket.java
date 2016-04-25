package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Влад on 08.04.2016.
 *
 * FileStorageStrategy будет представлять из себя коллекцию, но вместо бакетов будет использовать файлы.
 * Бакет - элемент хеш-таблицы, в котором находятся данные, записанные в нее.
 */
public class FileBucket {

    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("tmp", null); //Создание временного файла в директории для временных файлов.
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
        path.toFile().deleteOnExit(); //Удаление файла по завершении работы программы.
    }

    /**
     * Размер файла.
     */
    public long getFileSize() {
        return path.toFile().length();
    }

    /**
     * Сериализует переданный Entry в файл.
     *
     * @param entry который нужно сериализовать.
     */
    public void putEntry(Entry entry) {
        try {
            FileOutputStream fos = new FileOutputStream(path.toFile());
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(entry);
            fos.close();
            outputStream.close();
        }
        catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    /**
     * Десериализует Entry из файла.
     *
     * @return десериализованный Entry.
     */
    public Entry getEntry() {
        Entry entry = null;
        if(path.toFile().length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(path.toFile());
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                Object object = inputStream.readObject();
                fis.close();
                inputStream.close();
                entry = (Entry)object;
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        return entry;
    }

    /**
     * Удаляет файл, на который указывает path.
     */
    public void remove() {
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
