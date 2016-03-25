package com.javarush.test.level20.lesson10.home03;

import java.io.Serializable;
import java.io.*;
/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable{
    public static class A {
        protected String name = "A";
        public A () {}
        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public B(String name) {
            super(name);
            this.name += name;
        }
        private void writeObject(ObjectOutputStream out) throws IOException
        {
            out.writeObject(name);
        }
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
        {
            name = (String)in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\file.txt"));
        Solution.B b = new Solution().new B("Name");
        out.writeObject(b);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\file.txt"));
        b = (B)in.readObject();
        System.out.println(b.name);
    }
}
