package com.example.CarDirectory.util;

import com.example.CarDirectory.model.CarFields;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class Utils {

    public boolean validateCriteria(String[] criteria) {
        return Arrays.stream(criteria).allMatch(x -> {
            String[] entry = x.split(":");
            String column = entry[0].trim().toLowerCase();
            return x.length() == 2 && Arrays.stream(CarFields.values())
                    .map(CarFields::getField)
                    .collect(Collectors.toCollection(HashSet::new))
                    .contains(column);
        }) && criteria.length == Arrays.stream(criteria)
                .map(x -> x.split(":")[0].trim().toLowerCase())
                .distinct()
                .count();
    }

    public boolean validateRawData(String rawData) {
        return rawData.split("=").length == 2;
    }

    public String toPrettyJSON(HashMap<String, Object> map) {
        StringBuilder builder = new StringBuilder("{\n");
        map.keySet().forEach(key -> builder
                .append(key)
                .append(":\t")
                .append(map.get(key))
                .append(",\n"));
        return builder.deleteCharAt(builder.lastIndexOf(",")).append('}').toString();
    }

}
