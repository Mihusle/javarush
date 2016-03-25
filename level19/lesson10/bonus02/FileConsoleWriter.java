package com.javarush.test.level19.lesson10.bonus02;

import java.io.*;
/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

public class FileConsoleWriter extends FileWriter {

    public FileConsoleWriter(String fileName) throws IOException
    {
        super(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException
    {
        super(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException
    {
        super(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException
    {
        super(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd)
    {
        super(fd);
    }

    @Override
    public void write(int c) throws IOException
    {
        super.write(c);
        byte ch = (byte)c;
        char c1 = (char)ch;
        System.out.println(c1);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        super.write(cbuf, off, len);
        System.out.println(cbuf.toString().substring(off, len));
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        super.write(str, off, len);
        System.out.println(str.substring(off, len));
    }

    @Override
    public void write(char[] cbuf) throws IOException
    {
        super.write(cbuf);
        System.out.println(cbuf.toString());
    }

    @Override
    public void write(String str) throws IOException
    {
        super.write(str);
        System.out.println(str);
    }
}
