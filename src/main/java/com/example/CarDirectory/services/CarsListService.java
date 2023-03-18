package com.example.CarDirectory.services;

import org.springframework.stereotype.Service;

@Service
public class CarsListService {

    /**
     * Этот метод возвращает html-форму, позволяющую пользователю получать список записей
     * в соответствии с введёнными фильтрами.
     * @return Строка, которая соответствует html-документу ввода фильтров.
     */
    public String carsListResponse() {
        return "carsList";
    }

}
