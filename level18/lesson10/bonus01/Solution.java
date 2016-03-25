package com.javarush.test.level18.lesson10.bonus01;
 import java.io.*;
/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        if (args.length < 3) return;
        FileInputStream in = new FileInputStream(args[1]);
        FileOutputStream out = new FileOutputStream(args[2]);
        if (args[0].equals("-e"))
        {
            while (in.available() > 0)
            {
                int data = in.read();
                out.write(data + 1);
            }
        }
        else if (args[0].equals("-d")) {
            while (in.available() > 0) {
                int data = in.read();
                out.write(data - 1);
            }
        }
        in.close();
        out.close();
    }
}
