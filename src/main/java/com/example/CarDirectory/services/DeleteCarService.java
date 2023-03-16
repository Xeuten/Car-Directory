package com.example.CarDirectory.services;

import com.example.CarDirectory.model.Car;
import com.example.CarDirectory.persistence.CarRepository;
import com.example.CarDirectory.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class DeleteCarService {

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

    public String deleteCarResponse(String rawID, Model model) {
        if(!utils.validateRawData(rawID)) {
            throw new RuntimeException(denied);
        }
        String id = rawID.split("=")[1].trim();
        Optional<Car> car = carRepository.findById(id);
        if(car.isEmpty()) {
            throw new RuntimeException(String.format(carNotExists, id));
        }
        carRepository.delete(car.get());
        model.addAttribute("message", String.format(carDeleted, id));
        return "template1";
    }

}
