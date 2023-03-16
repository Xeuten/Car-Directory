package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.AddCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add_car")
public class AddCarController {

    @Autowired
    private AddCarService addCarService;

    @PostMapping
    public String addCar(@RequestBody String rawJSON, Model model) {
        return addCarService.addCarResponse(rawJSON, model);
    }

}
