package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.util.Map;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Влад on 07.03.2016.
 *
 * Основной класс сервера.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>(); //Все имена подключенных юзеров и соединения с ними.

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Введите порт сервера:");
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt()))
        {
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        }
        catch (Exception e) {
            ConsoleHelper.writeMessage("Ошибка.");
        }
    }

    /**
     * Отправляет сообщение всем подключенным юзерам.
     *
     * @param message - сообщение.
     * @exception IOException
     */
    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            try
            {
                pair.getValue().send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Ошибка при отправке сообщения.");
            }
        }
    }

    /**
     * Handler - обработчик.
     * Такой обработчик будет существовать для каждого сокета, поэтому наследуется от Thread.
     * Здесь происходит обмен сообщениями с клиентом.
     */
    private static class Handler extends Thread {
        private Socket socket; //Сокетное соединение для которого создается обработчик.

        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Знакомство с новым клиентом.
         * Производит запрос имени у нового пользователя,
         * получает его, добавляет в map пользователей
         *
         * @param connection - соединение с новым клиентом через сокет
         * @return имя нового пользователя.
         */
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String name;
            while (true)
            {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME)
                {
                    name = message.getData();
                    if (!name.equals("") && !connectionMap.containsKey(name))
                    {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return name;
        }

        /**
         * Отправляет новому пользователю информацию о пользователях уже подключенных к чату.
         *
         * @param connection - соединение с новым пользователем.
         * @param userName - имя нового пользователя.
         */
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for(Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }

        /**
         * Главный цикл обработки сообщений сервером.
         * Через соединение с пользователем получаем его сообщение,
         * отправляем всем пользователям (он свое сообщение тоже должен видеть)
         */
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message messageOfClient = connection.receive();
                if (messageOfClient.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + messageOfClient.getData()));
                }
                else {
                    ConsoleHelper.writeMessage("Ошибка при отправке сообщения.");
                }
            }
        }

        /**
         * Главный метод обработчика.
         *
         * @exception IOException
         * @exception ClassNotFoundException
         * */
        public void run() {
            ConsoleHelper.writeMessage("Соединение с удаленным адресом " + socket.getRemoteSocketAddress() + " установлено.");
            String userName = null;
            try (Connection connection = new Connection(socket))
            {
                userName = serverHandshake(connection); //Знакомство с новым пользователем.
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName)); //Отправка информации о новом пользователе всем пользователям.
                sendListOfUsers(connection, userName); //Отправка новому пользователю сообщения о подключенных пользователях.
                serverMainLoop(connection, userName); //Запуск главного цикла обработки сообщений.
            }
            catch (IOException | ClassNotFoundException e)
            {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом " + socket.getRemoteSocketAddress());
            }
            //Если обработали исключение и есть имя пользователя, то
            if (!userName.isEmpty()) {
                connectionMap.remove(userName); //Удалить его из map пользователей.
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName)); //Разослать всем прочим пользователям, что другой пользователь удален из чата.
            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом " + socket.getRemoteSocketAddress() + " закрыто.");
        }
    }
}
