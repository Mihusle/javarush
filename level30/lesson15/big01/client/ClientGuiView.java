package com.javarush.test.level30.lesson15.big01.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGuiView {
    private final ClientGuiController controller;

    private JFrame frame = new JFrame("Чат"); //Фрейм программы.
    private JTextField textField = new JTextField(50); //Поле ввода текста на 50 колонок.
    private JTextArea messages = new JTextArea(10, 50); //Текстовое поле для отображения сообщений на 10 строк и 50 колонок.
    private JTextArea users = new JTextArea(10, 10); //Текстовое поле для отображение списка подключенных пользователей на 10 строк и 10 колонок.

    public ClientGuiView(ClientGuiController controller) {
        this.controller = controller;
        initView();
    }

    /**
     * Метод, расставляющий все компоненты фрейма в нужной вариации и
     * обеспечивающий отправку введенного в поле текста по происшествии определенного события.
     */
    private void initView() {
        //Запрещаем изменение этих компонентов.
        textField.setEditable(false);
        messages.setEditable(false);
        users.setEditable(false);

        frame.getContentPane().add(textField, BorderLayout.NORTH); //На панель содержимого добавляем текстовое поле ввода сверху.
        frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST); //Добавляем прокрутку поля сообщений слева.
        frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST); //Добавляем прокрутку списка пользователей справа.
        frame.pack(); //Установка минимального размера фрейма достаточного для отображения всех компонентов.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //При нажатии на крестик закрываем программу.
        frame.setVisible(true); //Делаем фрейм видимым.

        textField.addActionListener(new ActionListener() { //Добавляем к полю ввода текста слушателя событий.
            //Когда происходит событие, вызывается этот метод.
            public void actionPerformed(ActionEvent e) { //В качестве параметра выступает объект указывающий на происшествие какого-то конкретного события.
                controller.sendTextMessage(textField.getText()); //Отправляем текст сообщения, введенный в это поле.
                textField.setText(""); //Устанавливаем значение этого поля по умолчанию.
            }
        });
    }

    /**
     * Запрашивает адрес сервера у пользователя, выводя диалоговое окно ввода.
     *
     * @return адрес сервера, введенный пользователем.
     */
    public String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame, //Ввод во фрейм, вроде. Тут сложно разобраться.
                "Введите адрес сервера:", //Сообщение для пользователя.
                "Конфигурация клиента", //Заголовок диалогового окна.
                JOptionPane.QUESTION_MESSAGE); //Тип сообщения диалогового окна.
    }

    /**
     * Запрашивает порт сервера, выводя диалоговое окно с запросом ввода этого порта.
     *
     * @exception Exception
     * @return порт сервера, введенный пользователем.
     */
    public int getServerPort() {
        while (true) {
            //Получаем значение порта сервера, выводя диалоговое окно с запросом ввода.
            String port = JOptionPane.showInputDialog(
                    frame,
                    "Введите порт сервера:",
                    "Конфигурация клиента",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                return Integer.parseInt(port.trim()); //Пробуем привести его к типу Integer и вернуть.
            } catch (Exception e) {
                //Если введен некорректный порт, то выводим диалоговое окно с сообщением об ошибке ввода и просим ввести порт еще раз.
                JOptionPane.showMessageDialog(
                        frame,
                        "Был введен некорректный порт сервера. Попробуйте еще раз.",
                        "Конфигурация клиента",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Запрашивает имя клиента, выводя диалоговое окно с запросом воода этого имени.
     *
     * @return имя пользователя, введенное пользователем.
     */
    public String getUserName() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите ваше имя:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Информирует о статусе подключения клиента к серверу
     */
    public void notifyConnectionStatusChanged(boolean clientConnected) {
        //Если пользователь подключен к чату, то поле ввода можно редактировать.
        textField.setEditable(clientConnected);
        if (clientConnected) {
            //Если пользователь подключен к чату, то выводим диалоговое окно, информирующее о том, что соединение с сервером установлено.
            JOptionPane.showMessageDialog(
                    frame,
                    "Соединение с сервером установлено",
                    "Чат",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            //Иначе выводим сообщение о том, что клиент не подключен к серверу.
            JOptionPane.showMessageDialog(
                    frame,
                    "Клиент не подключен к серверу",
                    "Чат",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Обновление сообщений чата.
     */
    public void refreshMessages() {
        messages.append(controller.getModel().getNewMessage() + "\n"); //В поле с сообщениями добавляем новое сообщение, полученное из модели контроллера, и переходим на новую строку.
    }

    /**
     * Обновление списка подключенных пользователей.
     */
    public void refreshUsers() {
        ClientGuiModel model = controller.getModel(); //Получаем модель контроллера.
        StringBuilder sb = new StringBuilder();
        for (String userName : model.getAllUserNames()) {
            sb.append(userName).append("\n"); //Строим строчку, которая выглядит как список имен подключенных пользователей.
        }
        users.setText(sb.toString()); //Вставляем этот текст в поле для отображения списка подключенных пользователей.
    }
}
