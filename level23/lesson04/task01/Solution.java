package com.javarush.test.level23.lesson04.task01;

/* Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] res = new Solution[2];
        Solution s1 = new Solution();
        Solution s2 = new Solution();
        Solution.InnerClass ic1 = s1.new InnerClass();
        Solution.InnerClass ic2 = s1.new InnerClass();
        Solution.InnerClass ic3 = s2.new InnerClass();
        Solution.InnerClass ic4 = s2.new InnerClass();
        s1.innerClasses[0] = ic1;
        s1.innerClasses[1] = ic2;
        s2.innerClasses[0] = ic3;
        s2.innerClasses[1] = ic4;
        res[0] = s1;
        res[1] = s2;
        return res;
    }
}
