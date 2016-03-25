package com.javarush.test.level22.lesson09.task02;

import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> pair : params.entrySet()) {
            if (pair.getValue() != null) {
                if (builder.toString().equals("")) {
                    builder.append(pair.getKey()).append(" = '").append(pair.getValue()).append("'");
                }
                else {
                    builder.append(" and ").append(pair.getKey()).append(" = '").append(pair.getValue()).append("'");
                }
            }
        }
        return builder;
    }

}
