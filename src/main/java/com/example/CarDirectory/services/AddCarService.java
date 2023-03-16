package com.example.CarDirectory.services;

import com.example.CarDirectory.model.Car;
import com.example.CarDirectory.model.CarWithoutDate;
import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
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

    public String addCarResponse(String rawJSON, Model model){
        if(!utils.validateRawData(rawJSON)) {
            throw new RuntimeException(denied);
        }
        CarWithoutDate car = new Gson().fromJson(rawJSON.split("=")[1], CarWithoutDate.class);
        if (carRepository.findById(car.registration_id).isPresent()) {
            throw new RuntimeException(String.format(carExists, car.registration_id));
        }
        carRepository.save(new Car(car));
        model.addAttribute("message", String.format(carSaved, car.registration_id));
        return "template1";
    }

}
