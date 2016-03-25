package com.javarush.test.level26.lesson02.task01;

import java.util.Arrays;
import java.util.Comparator;

/* Почитать в инете про медиану выборки
Реализовать логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы
Вернуть отсортированный массив от минимального расстояния до максимального
Если удаленность одинаковая у нескольких чисел, то выводить их в порядке возрастания
*/
public class Solution {
    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Integer[] searchMedian = array;
        Arrays.sort(searchMedian);
        final double median;
        if (searchMedian.length % 2 == 1) {
            median = searchMedian[(searchMedian.length - 1) / 2];
        }
        else {
            median = (double) (searchMedian[searchMedian.length/2 - 1] + searchMedian[searchMedian.length/2])/2;
        }
        System.out.println(median);
        Comparator<Integer> comparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int distance1 = Math.abs((int)(o1 - median));
                int distance2 = Math.abs((int)(o2 - median));
                if (distance1 == distance2) {
                    if (o1 > o2) {
                        distance1++;
                    }
                    else {
                        distance2++;
                    }
                }
                int result = distance1 - distance2;
                return result;
            }
        };
        Arrays.sort(array, comparator);
        return array;
    }

    /*public static void main(String[] args)
    {
        Integer[] array = new Integer[] {5,8,15,17};
        sort(array);
        for (Integer i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }*/
}
