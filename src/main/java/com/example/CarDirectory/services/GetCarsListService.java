package com.example.CarDirectory.services;

import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class GetCarsListService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    @Value("${messages.incorrect_filters}")
    private String incorrectFilters;

    public String getCarsListResponse(String filters, Model model) {
        HashMap<String, String> criteria = new HashMap<>();
        String[] criteriaArr = filters.split(",");
        if(!utils.validateCriteria(criteriaArr)){
            throw new RuntimeException(incorrectFilters);
        }
        Arrays.stream(criteriaArr).forEach(x -> {
            String[] entry = x.split(":");
            criteria.put(entry[0].trim().toLowerCase(), entry[1].trim());
        });
        return "";
    }

}
