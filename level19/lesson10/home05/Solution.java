package com.javarush.test.level19.lesson10.home05;

import java.io.*;
/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        BufferedWriter out = new BufferedWriter(new FileWriter(args[1]));
        while (in.ready())
        {
            String[] strings = in.readLine().split(" ");
            for (String line : strings)
            {
                if (line.matches(".*\\d.*"))
                {
                    out.write(line + " ");
                }
            }
        }
        in.close();
        out.close();
    }
}
