package com.javarush.test.level30.lesson15.big01;

import java.io.*;
/**
 * Created by Влад on 07.03.2016.
 *
 * Класс сообщений. Должны иметь возможность быть сериализованы и десериализованы.
 * Их пользователи отправляют серверу, а он другим пользователям.
 */
public class Message implements Serializable
{
    private final MessageType type;
    private final String data; //Содержимое сообщения.

    public Message(MessageType type)
    {
        this.type = type;
        data = null;
    }

    public Message(MessageType type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public MessageType getType()
    {
        return type;
    }

    public String getData()
    {
        return data;
    }
}
