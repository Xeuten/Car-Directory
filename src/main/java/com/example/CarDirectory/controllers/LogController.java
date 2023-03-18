package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping
    public String log(Model model) {
        return logService.logResponse(model);
    }

}
