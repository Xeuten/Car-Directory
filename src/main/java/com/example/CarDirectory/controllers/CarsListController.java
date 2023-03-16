package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.CarsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cars_list")
public class CarsListController {

    @Autowired
    private CarsListService carsListService;

    @GetMapping
    public String carsList() {
        return carsListService.carsListResponse();
    }

}
