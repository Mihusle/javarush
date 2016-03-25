package com.javarush.test.level27.lesson15.big01.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 31.01.2016.
 * Хранилище рекламных роликов
 * Одно, поэтому используем singleton (ленивая реализация)
 */
class AdvertisementStorage
{
    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        /*add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));*/

        videos.add(new Advertisement(someContent, "[Video 90sec 90]", 90, 1, 90));
        videos.add(new Advertisement(someContent, "[Video 60sec 200]", 200, 1, 60));
        videos.add(new Advertisement(someContent, "[Video 60sec 70]", 70, 1, 60));
        videos.add(new Advertisement(someContent, "[Video 60sec 40]", 40, 1, 60));
        videos.add(new Advertisement(someContent, "[Video 59sec 300]", 300, 1, 59));
        videos.add(new Advertisement(someContent, "[Video 59sec 100]", 100, 1, 59));
        videos.add(new Advertisement(someContent, "[Video 59sec 40]", 40, 1, 59));
        videos.add(new Advertisement(someContent, "[Video 36sec 60]", 60, 1, 36));
        videos.add(new Advertisement(someContent, "[Video 36sec 30]", 30, 1, 36));
        videos.add(new Advertisement(someContent, "[Video 24sec 90]", 90, 1, 24));
        videos.add(new Advertisement(someContent, "[Video 24sec 60]", 60, 1, 24));
        videos.add(new Advertisement(someContent, "[Video 24sec 30]", 30, 1, 24));
        videos.add(new Advertisement(someContent, "[Video 20sec 80]", 80, 1, 20));
        videos.add(new Advertisement(someContent, "[Video 20sec 50]", 50, 1, 20));
        videos.add(new Advertisement(someContent, "[Video 20sec 20]", 20, 1, 20));
        videos.add(new Advertisement(someContent, "[Video 20sec 10]", 10, 1, 20));
        videos.add(new Advertisement(someContent, "[Video 12sec 80]", 80, 1, 12));
        videos.add(new Advertisement(someContent, "[Video 12sec 50]", 50, 1, 12));
        videos.add(new Advertisement(someContent, "[Video 12sec 20]", 20, 1, 12));
        videos.add(new Advertisement(someContent, "[Video 12sec 10]", 10, 1, 12));
    }

    public static AdvertisementStorage getInstance() {
        if (instance == null) {
            instance = new AdvertisementStorage();
        }
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
