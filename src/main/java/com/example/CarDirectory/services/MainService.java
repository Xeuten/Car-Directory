package com.example.CarDirectory.services;

import org.springframework.stereotype.Service;

@Service
public class MainService {

    /**
     * Этот метод возвращает html-форму главного меню, позволяющего пользователю выбирать
     * действия.
     * @return Строка, соответствующая html-документу главного меню.
     */
    public String mainResponse() {
        return "main";
    }

}
