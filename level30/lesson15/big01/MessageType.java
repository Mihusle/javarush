package com.javarush.test.level30.lesson15.big01;

/**
 * Created by Влад on 07.03.2016.
 *
 * Сообщения бывают различных типов для описания различных событий.
 * Пользователи отправляют друг другу лишь сообщения типа TEXT,
 * прочие для сервера.
 */
public enum MessageType
{
    NAME_REQUEST,
    USER_NAME,
    NAME_ACCEPTED,
    TEXT,
    USER_ADDED,
    USER_REMOVED
}
