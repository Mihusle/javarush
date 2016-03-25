package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 24.02.2016.
 */
public class MoikrugStrategy implements Strategy
{

    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s"; //url-адрес, по которому можно получить вакансии.

    /**
     * Метод, собирающий вакансии с сайта.
     *
     * @param searchString - поисковый запрос.
     * @return Список всех найденных вакансий со всех страниц, начиная с 0.
     */
    @Override
    public List<Vacancy> getVacancies(String searchString) throws IOException
    {
        List<Vacancy> result = new ArrayList<>();
        try
        {
            Document document;
            int page = 1;
            while (true)
            {
                document = getDocument(searchString, page++); //Получаем Document по динамическому url
                if (document == null) break; //Если страницы заканчиваются, то завершаем работу.
                Elements elements = document.getElementsByAttributeValue("class", "job"); //Получаем все элементы отражающие вакансию на сайте.
                if (elements.size() == 0) break; //Если вакансии закончились, завершаем работу.
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setUrl("https://moikrug.ru" + element.getElementsByTag("a").first().attr("href")); //Получаем url вакансии.
                    vacancy.setTitle(element.getElementsByAttributeValue("class", "title").attr("title")); //Получаем название вакансии.
                    vacancy.setCity(element.getElementsByAttributeValue("class", "location").text()); //Получаем город, в котором находится вакансия.
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text()); //Получаем название компании, которая предлагает вакансию.
                    vacancy.setSiteName("moikrug.ru"); //Имя сайта всегда постоянно
                    Element salaryElement = element.getElementsByAttributeValue("class", "salary").first(); //Получаем элемент для зарплаты.
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text(); //Если зарплата указана, то получаем ее значение.
                    }
                    vacancy.setSalary(salary); //Записываем полученное значение или пустую строку.
                    result.add(vacancy); //Добавляем вакансию в список.
                }

                Elements elementsMarked = document.getElementsByAttributeValue("class", "job marked");
                if (elementsMarked.size() == 0) break;
                for (Element element : elementsMarked) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setUrl("https://moikrug.ru" + element.getElementsByTag("a").first().attr("href")); //Получаем url вакансии.
                    vacancy.setTitle(element.getElementsByAttributeValue("class", "title").attr("title")); //Получаем название вакансии.
                    vacancy.setCity(element.getElementsByAttributeValue("class", "location").text()); //Получаем город, в котором находится вакансия.
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text()); //Получаем название компании, которая предлагает вакансию.
                    vacancy.setSiteName("moikrug.ru"); //Имя сайта всегда постоянно
                    Element salaryElement = element.getElementsByAttributeValue("class", "salary").first(); //Получаем элемент для зарплаты.
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text(); //Если зарплата указана, то получаем ее значение.
                    }
                    vacancy.setSalary(salary); //Записываем полученное значение или пустую строку.
                    result.add(vacancy); //Добавляем вакансию в список.
                }
            }
        }
        catch (Exception e) {
            //Ignored...
        }
        return result;
    }

    /**
     * Метод создающий url и его объект Document.
     *
     * @param searchString - поисковый запрос.
     *
     * @return объект класса Document для url с данным поисковым запросом.
     */
    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, page, searchString);
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                    .referrer("http://javarush.ru/")
                    .get(); //Получаем документ по url.
        }
        catch (IOException e) {

        }
        document.html();
        return document;
    }
}
