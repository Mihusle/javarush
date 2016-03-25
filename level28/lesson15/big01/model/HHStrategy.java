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
 * Created by Влад on 13.02.2016.
 * Конкретная стратегия для работы с сайтом hh.ru или hh.ua
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d"; //URL-адрес, по которому можно получить вакансии

    /**
     * Метод, собирающий вакансии с сайта
     *
     * @param searchString - поисковый запрос
     * @return Список всех найденных вакансий со всех страниц, начиная с 0
     */
    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> result = new ArrayList<>(); //Список вакансий
        try //Т.к. в задании сказано игнорировать все исключения, то засунем все в блок try и добавим пустой catch
        {
            Document document; //Документ, который мы будем получать с каждой новой страницы
            int page = 0; //Номер страницы
            while (true) //Выполняем, пока не кончатся страницы с вакансиями
            {
                document = getDocument(searchString, page++); //Получаем документ с некоторым поисковым запросом searchString и номером страницы page
                if (document == null) break; //Завершаем цикл, если он пуст
                /*
                * Класс Elements - грубо говоря, список объектов класса Element.
                * Получаем этот список из документа с помощью метода getElementsByAttributeValue(),
                * с параметрами data-qa - атрибут и vacancy-serp__vacancy - значение атрибута.
                * Весь блок HTML-кода с таким атрибутом и с таким значением - вакансия.
                * Класс Element позволяет получить доступ к блоку HTML-кода по определенным атрибутам и значениям.
                * Т.е. теперь можно анализировать блок HTML-кода, описывающий ваканскию и вытаскивать оттуда необходимые данные
                * с помощью все того же класса Element.
                */
                Elements elements = document.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elements.size() == 0) break; //Завершаем цикл, если нет вакансий на странице
                for (Element element : elements)
                {
                    Vacancy vacancy = new Vacancy();
                    /*
                    * Записываем в вакансию ее url. Это делается тем же методом, но уже с иными параметрами.
                    * Находим элемент с атрибутом data-qa и его значением vacancy-serp__vacancy-title
                    * и url вакансии - это значение атрибута href. Получаем его с помощью метода attr() с параметром href.
                    */
                    vacancy.setUrl(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").attr("href"));
                    //Получаем необходимый элемент, указывая нужный атрибут и его значение, а затем вызываем метод text(), чтобы получить значение, которое находится внутри данного тега
                    vacancy.setTitle(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").text());
                    vacancy.setCity(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").text());
                    vacancy.setSiteName("http://hh.ua"); //Это значение известно заранее
                    /*
                    * Создаем элемент для зарплаты, который получаем с помощью метода first(), который вернет первый соответствующий элемент.
                    * Если этот элемент сущестует, то значение внутри тега с этим атрибутом и его значением занести в поле зарплаты.
                    * В ином же случае туда должна быть занесена пустая строка.
                    */
                    Element salaryElement = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first();
                    String salary = "";
                    if (salaryElement != null) {
                        salary = salaryElement.text();
                    }
                    vacancy.setSalary(salary);
                    result.add(vacancy);
                }
            }
        }
        catch (Exception e) {
            //Ignored...
        }
        return result;
    }

    /**
    * Метод, создающий url страницы и возвращающий ее объект Document/
     * @param searchString - поисковый завпрос, который отображается в url
     * @param page - номер страницы в url
     * @return объект Document, содержащий в себе элементы веб-страницы по созданному url.
    * */
    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page); //Не знаю, что там с закешированной страницей. Делаем url с переданными параметром
        Document document = null;
        try
        {
            document = Jsoup.connect(url).userAgent("Mozilla/5.0 (jsoup)").referrer("some text").timeout(5000).get(); //Получаем документ по url. Таймаут не знаю зачем.
                /*.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36")
                .referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")

                Это значения из браузера. Не знаю, прошло бы с ними или нет.
                */
        }
        catch (IOException e) {

        }
        return document;
    }
}
