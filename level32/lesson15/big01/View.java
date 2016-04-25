package com.javarush.test.level32.lesson15.big01;

import com.javarush.test.level32.lesson15.big01.listeners.FrameListener;
import com.javarush.test.level32.lesson15.big01.listeners.TabbedPaneChangeListener;
import com.javarush.test.level32.lesson15.big01.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Влад on 03.04.2016.
 *
 * Отвечает за отображение информации (визуализацию).
 * Наследуется от JFrame, т.к. создаем gui. Реализует ActionListener для обработки событий.
 *
 * Графический интерфейс будет представлять собой окно, в котором будет меню и панель с
 двумя вкладками.
 На первой вкладке будет располагаться текстовая панель, которая будет отрисовывать html
 страницу. На ней можно будет форматировать и редактировать текст страницы.
 На второй вкладке будет редактор, который будет отображать код html страницы, в нем будут
 видны все используемые html теги. В нем также можно будет менять текст страницы,
 добавлять и удалять различные теги.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); //Панель с двумя вкладками.
    private JTextPane htmlTextPane = new JTextPane(); //Первая вкладка для визуального редактирования html.
    private JEditorPane plainTextPane = new JEditorPane(); //Вторая для редактирования html в виде текста. Будет отображать теги и их содержимое.
    private UndoManager undoManager = new UndoManager(); //UndoManager управляет списком UndoableEdits, предоставляя возможность отменить или повторить соответствующие изменения.
    private UndoListener undoListener = new UndoListener(undoManager); //Слушатель правок, которые можно отменить или вернуть.

    public View() {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //Установка вида и поведения представление согласно модели операционной системы.
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    /*
    * Будет вызваться при выборе пунктов меню, у
    которых наше представление указано в виде слушателя событий.
    */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand(); //По этой строке можно понять, который из пунктов меню создал событие.
        switch (command) {
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                this.showAbout();
                break;
        }
    }

    /*
    * Вставляет html-документ в первую вкладку.
    */
    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    /*
    * Инициализация View.
    */
    public void init() {
        initGui(); //Инициализаця gui.
        addWindowListener(new FrameListener(this)); //Добавление слушателя событий.
        setVisible(true); //View стал видимым.
    }

    /*
    * Инициализация панели меню.
    */
    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar(); //Панель меню.
        MenuHelper.initFileMenu(this, jMenuBar); //Инициализация на панели меню "Файл".
        MenuHelper.initEditMenu(this, jMenuBar); //Инициализация на панели меню "Редактировать".
        MenuHelper.initStyleMenu(this, jMenuBar); //Инициализация на панели меню "Стиль".
        MenuHelper.initAlignMenu(this, jMenuBar); //Инициализация на панели меню "Выравнивание".
        MenuHelper.initColorMenu(this, jMenuBar); //Инициализация на панели меню "Цвет".
        MenuHelper.initFontMenu(this, jMenuBar); //Инициализация на панели меню "Шрифт".
        MenuHelper.initHelpMenu(this, jMenuBar); //Инициализация на панели меню "Помощь".
        getContentPane().add(jMenuBar, BorderLayout.NORTH); //Добавление панели меню в верхнуюю часть панели контента текущего фрейма.
    }

    /*
    * Инициализация редактора
    */
    public void initEditor() {
        htmlTextPane.setContentType("text/html"); //Установка типа контента для вкладки визуального редактирования.
        JScrollPane jScrollHtml = new JScrollPane(htmlTextPane); //Обеспечивает прокрутку компонента.
        tabbedPane.addTab("HTML", jScrollHtml); //Добавляем на панель компонент визуального редактирования с именем "HTML".
        JScrollPane jScrollPlain = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPlain); //Добавляем на панель компонент редактирования в виде текста с именем "Текст".
        tabbedPane.setPreferredSize(new Dimension(800, 600)); //Установка размера панели.
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this)); //Установка слушателя изменений панели для этого View.
        getContentPane().add(tabbedPane, BorderLayout.CENTER); //Размещение панели по центру панели контента текущего фрейма.
    }

    /*
    * Инициализация графического интерфейса.
    */
    public void initGui() {
        initMenuBar();
        initEditor();
        pack(); //Метод класса Window. Утсанавливает минимальный размер контейнера, которого достаточно для отображения всех элементов.
    }

    /*
    * Вызывается, когда произошла смена вкладки.
    */
    public void selectedTabChanged() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                controller.setPlainText(plainTextPane.getText()); //Если выбрана вкладка с html, то в документ контроллера записываем текст из второй вкладки.
                break;
            case 1:
                plainTextPane.setText(controller.getPlainText()); //Если выбрана вкладка с текстом, то записываем в нее текст из документа контроллера.
                break;
        }
        resetUndo(); //Сброс правок.
    }


    /*
    * Переключается на первую вкладку, сбрасывая все изменеия.
    */
    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    /*
     * Выбрана ли первая вкладка.
     */
    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0; //Вкладка html первая в списке => ее номер = 0.
    }

    /*
    * Проверяет доступна ли отмена действия.
    */
    public boolean canUndo() {
        return undoManager.canUndo();
    }

    /*
    * Отменяет последнее действие.
    */
    public void undo() {
        undoManager.undo();
    }

    /*
    * Проверяет доступен ли возврат действия.
    */
    public boolean canRedo() {
        return undoManager.canRedo();
    }

    /*
    * Возвращает ранее отмененное действие.
    */
    public void redo() {
        undoManager.redo();
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    /*
    * Вывод сообщения в виде диалогового окна и информацией о программе.
    */
    public void showAbout() {
        JOptionPane.showMessageDialog(this, "HTML Editor", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
     * Выход из программы
     */
    public void exit() {
        controller.exit();
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }
}
