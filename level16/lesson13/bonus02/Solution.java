package com.javarush.test.level16.lesson13.bonus02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Клубок
1. Создай 5 различных своих нитей c отличным от Thread типом:
1.1. нить 1 должна бесконечно выполняться;
1.2. нить 2 должна выводить "InterruptedException" при возникновении исключения InterruptedException;
1.3. нить 3 должна каждые полсекунды выводить "Ура";
1.4. нить 4 должна реализовать интерфейс Message, при вызове метода showWarning нить должна останавливаться;
1.5. нить 5 должна читать с консоли цифры пока не введено слово "N", а потом вывести в консоль сумму введенных цифр.
2. В статическом блоке добавь свои нити в List<Thread> threads в перечисленном порядке.
3. Нити не должны стартовать автоматически.
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<Thread>(5);

    static {
        threads.add(new OneClass());
        threads.add(new TwoClass());
        threads.add(new ThreeClass());
        threads.add(new FourClass());
        threads.add(new FiveClass());
    }

    public static class OneClass extends Thread {
        public void run() {
            while(true) {

            }
        }
    }

    public static class TwoClass extends Thread {
        public void run() {
            try
            {
                while (!isInterrupted()) {

                }
                throw new InterruptedException();
            }
            catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class ThreeClass extends Thread {
        public void run() {
            while(true) {
                try
                {
                    System.out.println("Ура");
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                }
            }
        }
    }

    public static class FourClass extends Thread implements Message {

        public void run() {
            Thread current = Thread.currentThread();
            while(!current.isInterrupted()) {

            }
        }

        public void showWarning() {
            this.interrupt();
            try
            {
                this.join();
            }
            catch (InterruptedException e)
            {

            }
        }
    }

    public static class FiveClass extends Thread {
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s;
            int sum = 0;
            try
            {
            while(!isInterrupted()) {
                    s = reader.readLine();
                    if (!s.equals("N")) sum += Integer.parseInt(s);
                    else {
                        this.interrupt();
                    }
                }
                throw new InterruptedException();
            }
            catch (IOException e)
            {

            }
            catch (InterruptedException e)
            {
                System.out.println(sum);
            }
        }
    }
}
