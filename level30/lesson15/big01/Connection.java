package com.javarush.test.level30.lesson15.big01;

import java.net.Socket;
import java.io.*;
import java.net.SocketAddress;

/**
 * Created by Влад on 07.03.2016.
 *
 * Класс соединения между клиентом и сервером.
 * Обертка над классом Socket, что умеет сериализовывать и десериализовывать сообщения типа Message.
 */
public class Connection implements Closeable
{
    private final Socket socket; //Сокетное соединение с клиентом.
    private final ObjectOutputStream out; //Осуществляет запись сообщений в сокет.
    private final ObjectInputStream in; //Осуществляет чтение сообщений из сокета.

    public Connection(Socket socket) throws IOException
    {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Отправка сообщения.
     * Синхронизирован по out, т.к. может отправляться сразу несколько сообщений.
     * Кроме того сохраняется доступ к другим методам класса.
     * Сериализация.
     * @param message - сообщение
     */
    public void send(Message message) throws IOException
    {
        synchronized (out) {
            out.writeObject(message);
        }
    }

    /**
     * Получение сообщений.
     * Десериализация.
     */
    public Message receive() throws IOException, ClassNotFoundException
    {
        synchronized (in) {
            return (Message) in.readObject();
        }
    }

    /**
     * Удаленный адрес сокетного соединения.
     */
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    /**
     * Закрытие всех потоков. Для этого класс реализует интерфейс Closeable.
     */
    @Override
    public void close() throws IOException
    {
        out.close();
        in.close();
        socket.close();
    }
}
