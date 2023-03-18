package com.example.CarDirectory.services;

import org.springframework.stereotype.Service;

@Service
public class DeleteMenuService {

    /**
     * Этот метод возвращает html-форму, позволяющую пользователю удалять записи из
     * справочника.
     * @return Строка, соответствующая html-документу удаления.
     */
    public String deleteMenuResponse() {
        return "deleteMenu";
    }

}
