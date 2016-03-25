package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Влад on 12.03.2016.
 *
 * Бот, работающий с датой. Это тоже клиент сервера, потому наследуется.
 */
public class BotClient extends Client
{

    public static void main(String[] args)
    {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    /**
     * Имя бота, которое генерируется со случайным номером
     */
    @Override
    protected String getUserName()
    {
        Random random = new Random();
        return "date_bot_" + random.nextInt(99);
    }

    /**
     * Если ввести что-то в консоль со стороны бота, то это не будет отправлено.
     */
    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    /**
     * Новый вспомогательный поток для бота
     */
    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    /**
     * Вспомогательный поток для бота, отвечающий за сокетное соединение и читающий сообщения сервера.
     */
    public class BotSocketThread extends SocketThread {

        /**
         * Основной цикл получения сообщений от сервера.
         */
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        /**
         * Обработка входящих сообщений
         *
         * @param message - текст входящего сообщения.
         */
        @Override
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                String[] nameAndText = message.split(": ");
                switch (nameAndText[1]) {
                    case "дата":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("d.MM.YYYY").format(Calendar.getInstance().getTime()));
                        break;
                    case "день":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("d").format(Calendar.getInstance().getTime()));
                        break;
                    case "месяц":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("MMMM").format(Calendar.getInstance().getTime()));
                        break;
                    case "год":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("YYYY").format(Calendar.getInstance().getTime()));
                        break;
                    case "время":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("H:mm:ss").format(Calendar.getInstance().getTime()));
                        break;
                    case "час":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("H").format(Calendar.getInstance().getTime()));
                        break;
                    case "минуты":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("m").format(Calendar.getInstance().getTime()));
                        break;
                    case "секунды":
                        sendTextMessage("Информация для " + nameAndText[0] + ": " + new SimpleDateFormat("s").format(Calendar.getInstance().getTime()));
                        break;
                }
            }
        }
    }
}
