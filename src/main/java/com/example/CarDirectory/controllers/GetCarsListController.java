package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.GetCarsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/get_cars_list")
public class GetCarsListController {

    @Autowired
    private GetCarsListService getCarsListService;

    @PostMapping
    public String getCarsList(@RequestBody String rawFilters, Model model) {
        return getCarsListService.getCarsListResponse(rawFilters, model);
    }

}
