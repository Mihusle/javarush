package com.javarush.test.level27.lesson15.big01.ad;

import java.util.*;

/**
 * Created by Влад on 31.01.2016.
 * Менеджер рекламных роликов
 * Подбирает оптимальную рекламу под каждый заказ
 * Взаимодействует с плеером для отображения роликов
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    /**
     * Обработка рекламного видео
     */
    public void processVideos() {
        List<Advertisement> videos = storage.list();
        videos = recSelection(videos);
        if (videos.isEmpty()) throw new NoVideoAvailableException();
        Collections.sort(videos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                //Первичная сортировка по убыванию стоимости показа
                int res = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (res != 0) return res;
                //Если стоимости показа видео одинаковы, то сортировка по возрастанию стоимости показа одной секунды видео (вторичная)
                long costFirst = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long costSecond = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();
                return Long.compare(costFirst, costSecond);
            }
        });
        for (Advertisement a : videos)
        {
            System.out.println(a.getName() +
                    " is displaying... " +
                    a.getAmountPerOneDisplaying() + ", " +
                    a.getAmountPerOneDisplaying() * 1000 / a.getDuration());
            a.revalidate();
        }
    }

    /*
* Рекурсивный метод, выделяющий наиболее выгодный список видео для показа
*/
    private List<Advertisement> recSelection(List<Advertisement> videos) {
        List<Advertisement> availableVideos = new ArrayList<>();
        int time = timeSeconds;
        //Так как список видео у нас уже отсортирован по убыванию стоимости одного показа,
        //то просто проходим по всем элементам поочередно с самого начала.
        for (Advertisement a : videos) {
            //Если элемент не содержится в списке доступных видео, и время его показа меньше оставшегося
            //доступного времени,
            if (a.getDuration() <= time && a.getHits() > 0)
            {
                //то добавляем это видео в список доступных, а время уменьшаем.
                availableVideos.add(a);
                time -= a.getDuration();
            }
        }
        //Если доступно для показа хотя бы одно видео,
        if (!availableVideos.isEmpty()) {
            //Создаем копию списка доступных видео, т.к. мы будем менять его в цикле по нему же
            List<Advertisement> copyAvailable = availableVideos;
            //Создаем копию исходного списка, т.к. будем удалять из него элементы в цикле
            List<Advertisement> copyVideos = videos;
            //Проходим по элементам списка доступных видео (в данном случае копии, т.к. основной, возможно, будет изменен)
            for (Advertisement a : copyAvailable) {
                //Чтобы рассмотреть все возможные варианты доступных для показа списков видео,
                //нужно поочередно удалять из исходного списка элементы. Тогда мы получим различные комбинации роликов,
                //которые ранее не могли быть в списке из-за других более длинных или более дорогих видео
                copyVideos.remove(a);
                //Рекурсия. Вызываем этот же метод для нового исходного списка, где одно видео удалено.
                //Соответственно внутри этого метода снова будет удалено следующее видео, вызван этот метод, там будет снова удалено видео и т.д.
                //Потом будет сделана итерация, будет удалено второе видео, а первое останется, далее снова будет вызван этот метод и там снова будут
                //удаляться все элементы поочередно по тому же принципу. Т.о. мы, на мой взгляд, рассмотрим все возможные доступные комбинации видео.
                copyVideos = recSelection(copyVideos);
                //Данная проверка необходима, т.к. метод внутри цикла может вернуть пустой список
                if (!copyVideos.isEmpty())
                {
                    //Из двух списков выбираем лучший. См. ниже.
                    availableVideos = bestList(availableVideos, copyVideos);
                }
                //Переприсваиваем копию исходного списка, т.к. изменяли ее ранее, но нам снова
                //нужен начальный вариант для проведения новой итерации цикла.
                copyVideos = videos;
            }
        }
        return availableVideos;
    }

    /*
    * Метод для подсчета общей стоимости показа набора роликов
    */
    private long totalAmount(List<Advertisement> list) {
        long total = 0;
        for (Advertisement a : list) {
            total += a.getAmountPerOneDisplaying();
        }
        return total;
    }

    /*
    * Метод для подсчета общего времени показа набора роликов
    */
    private int totalTime(List<Advertisement> list) {
        int total = 0;
        for (Advertisement a : list) {
            total += a.getDuration();
        }
        return total;
    }

    /*
    * Метод для выбора лучшего списка видео из двух
    */
    private List<Advertisement> bestList(List<Advertisement> best, List<Advertisement> candidat) {
        //Если общая стоимость ранее полученного лучшего списка меньше стоимости нового,
        //то новый - лучший.
        if (totalAmount(best) < totalAmount(candidat)) {
            best = candidat;
        }
        //Если же их стоимости равны, то
        else if (totalAmount(best) == totalAmount(candidat)) {
            //Если время показа видео из нового списка больше времени показа из
            //ранее полученного списка, то новый - лучший.
            if (totalTime(candidat) > totalTime(best)) {
                best = candidat;
            }
            //Если же времена показа видео из обоих списков равны, то
            else if (totalTime(candidat) == totalTime(best)) {
                //Если число видео в новом списке меньше числа видео в старом списке, то
                //новый - лучший.
                if (candidat.size() < best.size()) {
                    best = candidat;
                }
            }
        }
        return best;
    }
}
