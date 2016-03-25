package com.javarush.test.level18.lesson10.home07;
import java.io.*;
/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileReader in = new FileReader(reader.readLine());
        BufferedReader fileReader = new BufferedReader(in);
        String s;
        while ((s = fileReader.readLine()) != null) {
            if (s.startsWith(args[0] + " ")) {
                System.out.println(s);
                break;
            }
        }
        reader.close();
        in.close();
        fileReader.close();
    }
}
