package com.example.CarDirectory.persistence;

import com.example.CarDirectory.model.Car;

import java.util.HashMap;
import java.util.List;

public interface CarRepositoryCustom {

    List<Car> findCarsByCriteria(HashMap<String, String> criteria);

}
