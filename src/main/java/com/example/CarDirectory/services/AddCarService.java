package com.example.CarDirectory.services;

import com.example.CarDirectory.model.Car;
import com.example.CarDirectory.model.CarWithoutDate;
import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Log4j2
public class AddCarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Utils utils;

    @Value("${messages.car_exists}")
    private String carExists;

    @Value("${messages.car_saved}")
    private String carSaved;

    @Value("${messages.denied}")
    private String denied;

    @Value("${logging.messages.add_car}")
    private String addCar;

    /**
     * Этот метод сохраняет в базу запись об объекте, в соответствии с JSON'ом, введённым
     * пользователем. Если ввод некорректен, то метод выбрасывает соответствующее исключение.
     * @param rawJSON "Сырой" JSON - то есть JSON с префиксом, подлежащим удалению.
     * @param model Модель, которая будет передана Представлению.
     * @return Строка, соответствующая шаблону, заполненный сообщением об успешном
     * добавлении записи.
     */
    public String addCarResponse(String rawJSON, Model model){
        if(!utils.validateRawData(rawJSON)) {
            log.warn(addCar + denied);
            throw new RuntimeException(denied);
        }
        CarWithoutDate car = new Gson().fromJson(rawJSON.split("=")[1], CarWithoutDate.class);
        if(car == null) {
            throw new JsonSyntaxException("");
        }
        if(carRepository.findById(car.registration_id).isPresent()) {
            log.warn(addCar + String.format(carExists), car.registration_id);
            throw new RuntimeException(String.format(carExists, car.registration_id));
        }
        carRepository.save(new Car(car));
        log.info(addCar + String.format(carSaved), car.registration_id);
        model.addAttribute("message", String.format(carSaved, car.registration_id));
        return "template1";
    }

}
