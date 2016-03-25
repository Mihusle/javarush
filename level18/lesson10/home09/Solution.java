package com.javarush.test.level18.lesson10.home09;

import java.io.*;
/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        FileInputStream in = null;
        while (true) {
            try
            {
                fileName = reader.readLine();
                in = new FileInputStream(fileName);
            }
            catch (FileNotFoundException e)
            {
                System.out.println(fileName);
                break;
            }
        }
        reader.close();
        in.close();
    }
}