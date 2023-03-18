package com.example.CarDirectory.services;

import com.example.CarDirectory.model.Car;
import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class DeleteCarService {

    private static final Logger log = LogManager.getLogger(DeleteCarService.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    @Value("${messages.car_not_exists}")
    private String carNotExists;

    @Value("${messages.car_deleted}")
    private String carDeleted;

    @Value("${messages.denied}")
    private String denied;

    @Value("${logging.messages.delete_car}")
    private String deleteCar;

    /**
     * Этот метод удаляет из базы запись об объекте в соответствии с номером, введённым
     * пользователем. Если ввод некорректен, то выбрасывает соответствующее исключение.
     * @param rawID "Сырой" номер - то есть номер с префиксом, подлежащим удалению.
     * @param model Модель, которая будет передана Представлению.
     * @return Строка, соответствующая шаблону, заполненный сообщением об успешном
     * удалении записи.
     */
    public String deleteCarResponse(String rawID, Model model) {
        if(!utils.validateRawData(rawID)) {
            log.warn(deleteCar + denied);
            throw new RuntimeException(denied);
        }
        String id = rawID.split("=")[1].trim();
        Optional<Car> car = carRepository.findById(id);
        if(car.isEmpty()) {
            log.warn(deleteCar + String.format(carNotExists, id));
            throw new RuntimeException(String.format(carNotExists, id));
        }
        carRepository.delete(car.get());
        log.info(deleteCar + carDeleted);
        model.addAttribute("message", String.format(carDeleted, id));
        return "template1";
    }

}
