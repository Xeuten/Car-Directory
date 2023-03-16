package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.DeleteCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete_car")
public class DeleteCarController {

    @Autowired
    private DeleteCarService deleteCarService;

    @PostMapping
    public String deleteCar(@RequestBody String rawId, Model model) {
        return deleteCarService.deleteCarResponse(rawId, model);
    }

}
