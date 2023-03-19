package com.example.CarDirectory.util;

import com.example.CarDirectory.model.CarFields;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Utils {

    /**
     * Этот метод проверяет, имеют ли переданные критерии корректный формат. Корректным
     * форматом является "название_поля: значение_поля". Названия должны совпадать с
     * одним из значений перечисления CarFields, и также не повторяться.
     * @param criteria Массив строк критериев.
     * @return Истину, если формат критериев корректен и ложь, если он некорректен.
     */
    public boolean validateCriteria(String[] criteria) {
        return Arrays.stream(criteria).allMatch(x -> {
            String[] entry = x.split(":");
            String column = entry[0].trim().toLowerCase();
            return entry.length == 2 && Arrays.stream(CarFields.values())
                    .map(CarFields::getField)
                    .collect(Collectors.toCollection(HashSet::new))
                    .contains(column);
        }) && criteria.length == Arrays.stream(criteria)
                .map(x -> x.split(":")[0].trim().toLowerCase())
                .distinct()
                .count();
    }

    /**
     * Этот метод проверяет, совпадает ли формат данных с форматом, возвращаемым
     * html-формами.
     * @param rawData Проверяемая строка.
     * @return Истина, если формат корректен и ложь, если он некорректен.
     */
    public boolean validateRawData(String rawData) {
        return rawData.split("=").length == 2;
    }

    /**
     * Этот метод преобразует словарь в JSON-строку.
     * @param map Словарь.
     * @return JSON-строка.
     */
    public String toPrettyJSON(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder("{\n");
        map.keySet().forEach(key -> builder
                .append(key)
                .append(":\t")
                .append(map.get(key))
                .append(",\n"));
        return builder.deleteCharAt(builder.lastIndexOf(",")).append('}').toString();
    }

    /**
     * Этот метод преобразует русские названия полей в английские.
     * @param rusField Название поля на русском.
     * @return Название поля на английском.
     */
    public String rusToEng(String rusField) {
        return switch (rusField) {
            case "номер" -> "registration_id";
            case "марка" -> "vehicle_brand";
            case "цвет" -> "color";
            case "год выпуска" -> "year_of_manufacture";
            case "дата создания" -> "creation_date";
            default -> "";
        };
    }

}
