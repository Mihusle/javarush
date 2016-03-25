package com.javarush.test.level20.lesson10.home05;

import java.io.PrintStream;
import java.util.logging.Logger;
import java.io.*;

/* Сериализуйте Person
Сериализуйте класс Person стандартным способом. При необходимости поставьте полям модификатор transient.
*/
public class Solution {

    public static class Person implements Serializable{
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greetingString = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\file.txt"));
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\file.txt"));
        Person person = new Person("Ivan", "Ivanov", "Russia", Sex.MALE);
        out.writeObject(person);
        person = (Person)in.readObject();
        System.out.println(person.firstName + " " + person.country + " " + person.lastName + " " + person.fullName + " " + person.greetingString + " "
                + person.sex + " " + person.outputStream + " " + person.logger);
    }
}
