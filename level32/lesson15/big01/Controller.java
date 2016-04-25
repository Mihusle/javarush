package com.javarush.test.level32.lesson15.big01;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by Влад on 03.04.2016.
 *
 * Обеспечивает связь между пользователем и системой: контролирует ввод данных пользователем и использует модель и представление для реализации необходимой реакции.
 */
public class Controller
{
    private View view; //Связь с View.
    private HTMLDocument document; //Модель. Документ, с которым работает редактор.
    private File currentFile; //Файл открытый в настоящий момент.

    public Controller(View view)
    {
        this.view = view;
    }

    /*
    * Инициализация контроллера.
    */
    public void init() {
        createNewDocument(); //Создает новый документ.
    }

    /**
     * Записывает текст в документ.
     *
     * @param text текст, который нужно записать.
     */
    public void setPlainText(String text) {
        resetDocument(); //Сброс документа.
        StringReader reader = new StringReader(text); //Реадер на основе переданного текста.
        try
        {
            new HTMLEditorKit().read(reader, document, 0); //Вычитываем текст из реадера в документ.
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
    }

    /**
     * Получает из документа текст со всеми html-тегами.
     *
     * @return весь текст документа.
     */
    public String getPlainText() {
        StringWriter writer = new StringWriter();
        try
        {
            new HTMLEditorKit().write(writer, document, 0, document.getLength()); //Переписывание всего текста документа во writer.
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
        catch (BadLocationException e)
        {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    /*
    * Сбрасывает текущий документ.
    */
    public void resetDocument() {
        if (document != null)
        {
            document.removeUndoableEditListener(view.getUndoListener()); //Сброс слушателя правок с текущего документа
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument(); //Создание нового документа по умолчанию. Используется HTMLEditorKit, т.к. html-документ.
        document.addUndoableEditListener(view.getUndoListener()); //Установка новому документу слушателя правок.
        view.update(); //Обновляем представление. Новый документ появляется на первой вкладке.
    }

    /*
    * Создает новый документ
    */
    public void createNewDocument() {
        view.selectHtmlTab(); //Выбор вкладки с html.
        resetDocument(); //Сброс документа.
        view.setTitle("HTML редактор"); //Новый заголовок окна.
        view.resetUndo(); //Сброс правок в Undo-менеджере представления.
        currentFile = null; //Обнуление текущего файла.
    }

    public void openDocument() {
        view.selectHtmlTab(); //Выбор вкладки с html.
        JFileChooser jFileChooser = new JFileChooser(); //Объект для выбора файла.
        jFileChooser.setFileFilter(new HTMLFileFilter()); //Установка для него фильтра.
        int choose = jFileChooser.showOpenDialog(view); //Вывод диалогового окна "Open File" для выбора файла.
        if (choose == JFileChooser.APPROVE_OPTION) {
            //Если файл выбран, то
            currentFile = jFileChooser.getSelectedFile(); //Теперь этот файл является текущим.
            resetDocument(); //Сбрасываем документ.
            view.setTitle(currentFile.getName()); //Новое имя представления.
            try (FileReader reader = new FileReader(currentFile))
            {
                new HTMLEditorKit().read(reader, document, 0); //Переписываем все из файла в html-документ. Используем HTMLEditorKit, потому что работает с html кодом.
                view.resetUndo(); //Сброс правок документа.
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }

        }
    }

    /*
    * Сохраняет открытый файл (currentFile).
    */
    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
            view.setTitle(currentFile.getName()); //Устанавливаем заголовком представления его имя.
            try (FileWriter writer = new FileWriter(currentFile)) //На его основе делаем FileWriter
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength()); //Переписываем в него весь текст документа.
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }

    /*
    * Сохранить документ как...
    */
    public void saveDocumentAs() {
        view.selectHtmlTab(); //Выбор вкладки с html.
        JFileChooser jFileChooser = new JFileChooser(); //Объект для выбора файла.
        jFileChooser.setFileFilter(new HTMLFileFilter()); //Установка для него фильтра.
        int choose = jFileChooser.showSaveDialog(view); //Вывод диалогового окна "Save File" для выбора файла.
        if (choose == JFileChooser.APPROVE_OPTION) {
            //Если выбор файла подтвержден, то
            currentFile = jFileChooser.getSelectedFile(); //Делаем этот файл текущим.
            view.setTitle(currentFile.getName()); //Устанавливаем заголовком представления его имя.
            try (FileWriter writer = new FileWriter(currentFile)) //На его основе делаем FileWriter
            {
                new HTMLEditorKit().write(writer, document, 0, document.getLength()); //Переписываем в него весь текст документа.
            }
            catch (Exception e)
            {
                ExceptionHandler.log(e);
            }
        }
    }
    /*
    * Выход из программы.
    */
    public void exit() {
        System.exit(0);
    }

    public HTMLDocument getDocument()
    {
        return document;
    }

    public static void main(String[] args)
    {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init(); //Инициализация view.
        controller.init(); //Инициализация controller.

    }
}
