package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.DeleteMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete_car_menu")
public class DeleteMenuController {

    @Autowired
    private DeleteMenuService deleteMenuService;

    @GetMapping
    public String deleteMenu() {
        return deleteMenuService.deleteMenuResponse();
    }

}
