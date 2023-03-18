package com.example.CarDirectory.services;

import org.springframework.stereotype.Service;

@Service
public class AddMenuService {

    /**
     * Этот метод возвращает html-форму, позволяющую пользователю добавлять записи в справочник.
     * @return Строка, которая соответствует html-документу добавления.
     */
    public String addMenuResponse() {
        return "addMenu";
    }

}
