package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.AddMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add_car_menu")
public class AddMenuController {

    @Autowired
    private AddMenuService addMenuService;

    @GetMapping
    public String addMenu() {
        return addMenuService.addMenuResponse();
    }

}
