package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.io.*;

/**
 * Created by Влад on 21.02.2016.
 * Конкретный View для работы с html. Содержит ссылку на controller, чтобы передавать данные о событиях.
 */
public class HtmlView implements View
{
    private Controller controller; //Для передачи данных.
    private final String filePath = "./src/" + this.getClass().getPackage().toString().substring(8).replace('.', '/') + "/vacancies.html"; //Относительный адрес файла, в который будет писаться html с вакансиями.

    /**
     * Обновляет файл для списка вакансий, используя полученный html-код.
     *
     * @param vacancies - список вакансий, данные которых должны быть записаны в файл и представлены пользователю.
     */
    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            updateFile(getUpdatedFileContent(vacancies));
            System.out.println(vacancies.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    /**
     * Как будто произошло какое-то событие.
     * Например, был введен поисковый запрос.
     * Теперь его необходимо передать в контроллер.
     */
    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Voronezh");
    }

    /**
     * Обновление контента файла.
     *
     * @param  list - список вакансий, которые необходимо добавить в файл.
     *
     * @return html-код обновленного файла.
     */
    private String getUpdatedFileContent(List<Vacancy> list) {
        Document document;
        try
        {
            document = getDocument(); //Получаем старый файл.
            Element element = document.getElementsByClass("template").first(); //Берем первый элемент с классом "template".
            Element elementCopy = element.clone(); //Создаем его копию. Нужна, потому что нам необходимо удалить некоторые данные, чтобы сделать шаблон для построения представления вакансии.
            elementCopy.removeClass("template").removeAttr("style"); //Удаляем из копии класс template и атрибут style. Получаем шаблон.
            document.getElementsByAttributeValue("class", "vacancy").remove(); //Удаляем блок html-кода с классом vacancy. То есть удаляем ранее добавленные вакансии из представления.
            for (Vacancy vacancy : list) {
                Element elementVacancy = elementCopy.clone(); //Создаем копию для каждого объекта вакансии. Нужна, потому что элемент будет меняться.
                elementVacancy.getElementsByAttributeValue("class", "city").first().text(vacancy.getCity()); //Добавляем город.
                elementVacancy.getElementsByAttributeValue("class", "companyName").first().text(vacancy.getCompanyName()); //Добавляем имя компании.
                elementVacancy.getElementsByAttributeValue("class", "salary").first().text(vacancy.getSalary()); //Добавляем зарплату.
                Element link = elementVacancy.getElementsByTag("a").first(); //Получаем элемент ссылки по тегу.
                link.text(vacancy.getTitle()); //Записываем в нее название вакансии.
                link.attr("href", vacancy.getUrl()); //Записываем реальный url вакансии.
                element.before(elementVacancy.outerHtml()); //Добавляем весь html-код этого элемента перед элементом с классом template. В итоге получаем список вакансий.
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }


    /**
     * Обновляет файл, записывая в него html-код.
     *
     * @param bodyOfFile - сам html-код.
     */
    private void updateFile(String bodyOfFile) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(bodyOfFile);
        bufferedWriter.close();
    }

    /**
     * Получает объект Document через относительный путь к файлу, в который пишем код с вакансиями.
     *
     * @return объект Document файла с вакансиями.*/
    protected Document getDocument() throws IOException {
        File file = new File(filePath);
        Document document = Jsoup.parse(file, "UTF-8");
        return document;
    }
}
