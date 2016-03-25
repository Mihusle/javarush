package com.javarush.test.level30.lesson15.big01.client;

import java.io.IOException;
import java.net.Socket;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

/**
 * Created by Влад on 11.03.2016.
 *
 * Клиент. Обменивается текстовыми сообщениями с сервером.
 */
public class Client
{
    protected Connection connection;
    private volatile boolean clientConnected = false; //true - клиент подсоединен к серверу.

    public static void main(String[] args)
    {
        Client client = new Client();
        client.run();
    }

    /**
     * Запрашивает адрес сервера.
     *
     * @return адрес сервера.
     */
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера: ");
        return ConsoleHelper.readString();
    }

    /**
     * Запрашивает порт сервера.
     *
     * @return порт сервера.
     */
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера: ");
        return ConsoleHelper.readInt();
    }

    /**
     * Запрашивает имя пользователя.
     *
     * @return имя пользователя.
     */
    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя: ");
        return ConsoleHelper.readString();
    }

    /**
     * В этой реализации всегда возвращает true, но в наследниках может быть переопределен,
     * если потребуется реализация клиента, которая не будет отправлять текст введенный в консоль.
     *
     * @return отправлять ли текст введенный в консоль.
     */
    protected boolean shouldSentTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    /**
     * Отправка текстового сообщения.
     */
    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("Ошибка при отправке сообщения серверу.");
            clientConnected = false;
        }

    }

    /**
     * Здесь основной функционал класса.
     */
    public void run() {
        SocketThread socketThread = getSocketThread(); //Новый вспомогательный поток.
        socketThread.setDaemon(true); //Становится демоном, чтобы быть закрытым вместе с программой.
        socketThread.start();
        synchronized (this) {
            try
            {
                wait(); //Ожидание нотификации от другого потока.
            }
            catch (InterruptedException e)
            {
                ConsoleHelper.writeMessage("Ошибка при ожидании нотификации.");
            }
        }
        if (clientConnected) { //Когда произошла нотификация
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        }
        else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        while (clientConnected) { //Пока клиент подключен к серверу можно отправлять сообщения, до введения 'exit'.
            String textMessage = ConsoleHelper.readString();
            if (textMessage.equals("exit")) break;
            if (shouldSentTextFromConsole()) {
                sendTextMessage(textMessage);
            }
        }
    }

    /**
     * Отвечает за поток, устанавливающий сокетное соединение и читающий сообщения с сервера.
     */
    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Пользователь " + userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Пользователь " + userName + " покинул чат.");
        }

        /**
         * Осуществляет нотификацию ожидающего основного потока.
         *
         * @param clientConnected - устанавливает состояние подключения клиента к серверу.
         */
        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        /**
         * Представляет клиента серверу.
         *
         * @exception IOException
         * @exception ClassNotFoundException
         */
        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message messageFromServer = connection.receive(); //Получение сообщения от сервера.
                if (messageFromServer.getType() == MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME, getUserName())); //Если это запрос имени, то отправить имя, создав его.
                }
                else if (messageFromServer.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true); //Если имя принято, то нотифицировать основной поток и выйти из метода.
                    return;
                }
                else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        /**
         * Главный цикл обработки сообщений сервера.
         *
         * @exception IOException
         * @exception ClassNotFoundException
         */
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) //Цикл прервется, если будет брошено исключение или поток прервется.
            {
                Message messageFromServer = connection.receive(); //Получаем сообщение от сервера.
                if (messageFromServer.getType() == MessageType.TEXT)
                {
                    processIncomingMessage(messageFromServer.getData()); //Если это текст, то выводим на экран в соответствующем виде.
                }
                else if (messageFromServer.getType() == MessageType.USER_ADDED)
                {
                    informAboutAddingNewUser(messageFromServer.getData()); //Если сообщение о добавлении пользователя, то выводим на экран в соответствующем виде.
                }
                else if (messageFromServer.getType() == MessageType.USER_REMOVED)
                {
                    informAboutDeletingNewUser(messageFromServer.getData()); //Если это сообщение об удалении пользователя, то выводим на экран в соответствующем виде.
                }
                else
                {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        /**
         * Основной метод класса.
         */
        public void run() {
            String serverAddress = getServerAddress(); //Адрес сервера.
            int serverPort = getServerPort(); //Порт сервера.
            try
            {
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake(); //Знакомтсво клиента с сервером.
                clientMainLoop(); //Цикл обработки сообщений от сервера.
            }
            catch (IOException | ClassNotFoundException e)
            {
                notifyConnectionStatusChanged(false); //При исключении оповещаем главный поток, отключаем клиента.
            }
        }
    }
}
