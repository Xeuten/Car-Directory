package com.example.CarDirectory.services;

import org.springframework.stereotype.Service;

@Service
public class CarsListService {

    public String carsListResponse() {
        return "carsList";
    }

}
