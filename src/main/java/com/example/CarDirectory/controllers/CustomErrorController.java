package com.example.CarDirectory.controllers;

import com.example.CarDirectory.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @Autowired
    private ErrorService errorService;

    @GetMapping
    public void error() {
        errorService.errorResponse();
    }

}
